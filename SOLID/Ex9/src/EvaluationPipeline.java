public class EvaluationPipeline {
    private final IPlagiarismChecker pc;
    private final ICodeGrader grader;
    private final IReportWriter writer;
    private final ILogger logger;
    private final Rubric rubric;

    public EvaluationPipeline(IPlagiarismChecker pc, ICodeGrader grader, IReportWriter writer, ILogger logger,
            Rubric rubric) {
        this.pc = pc;
        this.grader = grader;
        this.writer = writer;
        this.logger = logger;
        this.rubric = rubric;
    }

    public void evaluate(Submission sub) {
        int plag = pc.check(sub);
        logger.log("PlagiarismScore=" + plag);

        int code = grader.grade(sub, rubric);
        logger.log("CodeScore=" + code);

        String reportName = writer.write(sub, plag, code);
        logger.log("Report written: " + reportName);

        int total = plag + code;
        String result = (total >= 90) ? "PASS" : "FAIL";
        logger.log("FINAL: " + result + " (total=" + total + ")");
    }
}
