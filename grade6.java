import java.util.*;

public class grade6 {

    public static void main(String[] args) {
        List<Map<String, Object>> studentsData = initialize();
		
        Map<String, Object> result = calculate(studentsData);

        System.out.println("Average Grades for Each Student: " + result.get("average_grades"));
        System.out.println("Average Grade for Each Subject: " + result.get("average_subjects"));
        System.out.println("Overall Average Grade: " + result.get("overall_average"));
        System.out.println("Standard Deviation of Grades: " + result.get("std_deviation"));
    }

    private static List<Map<String, Object>> initialize() {
        List<Map<String, Object>> studentsData = new ArrayList<>();

        studentsData.add(createStudent("John Doe", 90, 85, 92, 88, 95));
        studentsData.add(createStudent("Jane Smith", 88, 92, 87, 90, 93));
        studentsData.add(createStudent("Bob Johnson", 78, 85, 80, 88, 82));

        return studentsData;
    }

    private static Map<String, Object> createStudent(String name, int... grades) {
        Map<String, Object> student = new HashMap<>();
        student.put("name", name);

        List<Map<String, Object>> gradeList = new ArrayList<>();
        String[] subjects = {"Math", "English", "Science", "History", "Art"};
        for (int i = 0; i < grades.length; i++) {
            Map<String, Object> grade = Map.of("subject", subjects[i], "grade", grades[i]);
            gradeList.add(grade);
        }

        student.put("grades", gradeList);
        return student;
    }

    public static Map<String, Object> calculate(List<Map<String, Object>> studentsData) {
        List<Double> averageGrades = new ArrayList<>();
        Map<String, Double> subjectSums = new HashMap<>();
        List<Integer> allGrades = new ArrayList<>();

        for (Map<String, Object> student : studentsData) {
            List<Map<String, Object>> studentGrades = extractStudentGrades(student);

            double averageGrade = calculateAverageGrade(studentGrades);
            averageGrades.add(averageGrade);

            for (Map<String, Object> grade : studentGrades) {
                String subject = (String) grade.get("subject");
                int gradeValue = (int) grade.get("grade");
                subjectSums.merge(subject, (double) gradeValue, Double::sum);
                allGrades.add(gradeValue);
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("average_grades", averageGrades);
        result.put("average_subjects", calculateSubjectAverages(subjectSums, studentsData.size()));
        result.put("overall_average", calculateOverallAverage(allGrades));
        result.put("std_deviation", calculateStandardDeviation(allGrades, result.get("overall_average")));

        return result;
    }

    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> extractStudentGrades(Map<String, Object> student) {
        return (List<Map<String, Object>>) student.get("grades");
    }

    private static double calculateAverageGrade(List<Map<String, Object>> grades) {
        return grades.stream()
                .mapToInt(grade -> (int) grade.get("grade"))
                .average()
                .orElse(0.0);
    }

    public static Map<String, Double> calculateSubjectAverages(Map<String, Double> subjectSums, int studentCount) {
        Map<String, Double> averageSubjects = new HashMap<>();
        subjectSums.forEach((subject, sum) -> averageSubjects.put(subject, sum / studentCount));
        return averageSubjects;
    }

    public static double calculateOverallAverage(List<Integer> allGrades) {
        return allGrades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public static double calculateStandardDeviation(List<Integer> allGrades, Object overallAverage) {
        double average = (double) overallAverage;
        double squaredDiffSum = allGrades.stream()
                .mapToDouble(grade -> Math.pow(grade - average, 2))
                .sum();
        double variance = squaredDiffSum / allGrades.size();
        return Math.sqrt(variance);
    }
}
