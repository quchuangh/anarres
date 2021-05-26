package com.chuang.anarres.service.single.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.anarres.enums.TaskStatus;
import com.chuang.anarres.mapper.DownloadTaskMapper;
import com.chuang.anarres.model.DownloadTask;
import com.chuang.anarres.service.single.IDownloadTaskService;
import com.chuang.tauceti.httpclient.Request;
import com.chuang.tauceti.httpclient.Response;
import com.chuang.tauceti.support.exception.BusinessException;
import com.chuang.tauceti.tools.basic.FileKit;
import com.chuang.tauceti.tools.basic.FutureKit;
import com.chuang.tauceti.tools.basic.URLKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ath
 * @since 2020-08-22
 */
@Service
@Slf4j
public class DownloadTaskServiceImpl extends ServiceImpl<DownloadTaskMapper, DownloadTask> implements IDownloadTaskService {


    @Value("${eden.fetch.save-path}") private String savePath;

    private void handError(DownloadTask task,String msg, boolean done) {
        task.setError(msg);
        task.setState(TaskStatus.ERROR);

        if(done) {
            task.setFailTimes(task.getFailTimes() + 1000);
        } else {
            task.setFailTimes(task.getFailTimes() + 1);
        }

        if(task.getFailTimes() > 5) {
            task.setState(TaskStatus.ENDED);
        }

        updateById(task);
    }

    public CompletableFuture<Response> download(DownloadTask task, boolean checkFile) {
        if(checkFile) {
            if(FileKit.exist(savePath + task.getSavePath())) {
                return FutureKit.error(new BusinessException(-1, "文件已经存在"));
            }
        }

        log.info("开始下载:" + task.getUrl());
//        getById(task.setState(TaskStatus.RUNNING));
        CompletableFuture<Response> future;
        try {
            future = Request.Get(URLKit.decode(task.getUrl(), "utf-8")).build().asyncExecute();
        } catch (Exception e) {
            handError(task, e.getMessage(), false);
            return FutureKit.error(e);
        }

        return future.whenComplete((response, throwable) -> {
            if (null != throwable) {
                log.error("下载失败", throwable);
                handError(task, throwable.getMessage(), false);
                return;
            }
            if(response.getStatusCode() == 200) {
                try {
                    FileKit.writeFromStream(response.asStream(), savePath + task.getSavePath());
                    log.info("文件保存到：" + savePath + task.getSavePath());
                    task.setError("");
                    task.setState(TaskStatus.ENDED);
                    updateById(task);
                } catch (IOException e) {
                    handError(task, e.getMessage(), true);
                    throw new BusinessException(-1, "写入失败", e);
                } finally {
                    response.close();
                }
            } else {
                log.error("下载失败, 地址：{}，code:{}", task.getUrl(), response.getStatusCode());
                handError(task, "网络状态：" + response.getStatusCode(), true);
                throw new BusinessException(-1, "网络状态：" + response.getStatusCode());
            }
        });
    }
}
