package com.xiangzhurui.example.demo.restful;

/**
 * @author xiangzhurui
 * @version 2019-04-28 10:26
 */
public class MyCompiler {

    public static void main(String[] args) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        //获取java文件管理类
        StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
        //获取java文件对象迭代器
        Iterable<? extends JavaFileObject> it = manager.getJavaFileObjects(files);
        //设置编译参数
        ArrayList<String> ops = new ArrayList<String>();
        ops.add("-Xlint:unchecked");
        //设置classpath
        ops.add("-classpath");
        ops.add(CLASS_PATH);
        //获取编译任务
        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, ops, null, it);
        //执行编译任务
        task.call();
    }
}
