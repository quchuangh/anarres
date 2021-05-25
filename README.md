### 问题说明11
1. IRelationService 在项目中并没有被使用。原因是过于复杂。且没有完成排序。
2. 字典暂时还没完全想明白，暂不实现

### 参数空格问题
1. AnrControllerAdvice.initBinder 会将参数中的字符串前后空格移除，
2. jackson2ObjectMapperBuilderCustomizer 会将json参数中前后空格移除 

“快乐有什么不对呢，塔科维亚？你为什么不想要呢？”
“快乐并没有什么不对，只是我并不需要快乐。如果我享用了自己并不需要的东西，那么永远也得不到我真正需要的东西了。“
