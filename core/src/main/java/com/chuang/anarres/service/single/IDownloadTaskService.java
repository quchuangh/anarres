package com.chuang.anarres.service.single;

import com.chuang.anarres.model.DownloadTask;
import com.chuang.tauceti.httpclient.Response;
import com.chuang.tauceti.rowquery.IRowQueryService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ath
 * @since 2020-08-22
 */
public interface IDownloadTaskService extends IRowQueryService<DownloadTask> {

    default Optional<DownloadTask> findByUrlAndWebSite(String url, String website) {
        return lambdaQuery().eq(DownloadTask::getUrl, url).eq(DownloadTask::getWebsite, website).oneOpt();
    }
    default List<DownloadTask> findByWebSite(String website) {
        return lambdaQuery().eq(DownloadTask::getWebsite, website).list();
    }

    default CompletableFuture<Response> download(DownloadTask task) {
        return this.download(task, false);
    }

    CompletableFuture<Response> download(DownloadTask task, boolean checkFile);
}
