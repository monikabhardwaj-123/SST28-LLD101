public class SimpleConsole implements ILogger {
    @Override
    public void log(String s) {
        System.out.println(s);
    }
}
