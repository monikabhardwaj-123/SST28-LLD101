public class Main {
    public static void main(String[] args) {
        System.out.println("=== Evaluation Pipeline ===");
        Submission sub = new Submission("23BCS1007", "public class A{}", "A.java");

        IPlagiarismChecker checker = new PlagiarismChecker();
        ICodeGrader grader = new CodeGrader();
        IReportWriter writer = new ReportWriter();
        ILogger logger = new SimpleConsole();
        Rubric rubric = new Rubric();

        EvaluationPipeline pipeline = new EvaluationPipeline(checker, grader, writer, logger, rubric);
        pipeline.evaluate(sub);
    }
}
