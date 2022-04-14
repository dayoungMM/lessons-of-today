package java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentForEachSample {
    public static void main(String[] args) {
        StudentForEachSample sample = new StudentForEachSample();
        List<StudentDTO> studentList = new ArrayList<>();
        studentList.add(new StudentDTO("요다",43,99,10));
        studentList.add(new StudentDTO("만두",30,71,20));
        studentList.add(new StudentDTO("건빵",32,64,40));
        sample.printStudentNames(studentList);
        sample.printNim(studentList);
        sample.collectName(studentList);
        sample.filterAgradeMath(studentList, 80);
    }

    private void printStudentNames(List<StudentDTO> students) {
        students.stream().forEach(student -> System.out.println(student.getName()));
    }

    private void printNim(List<StudentDTO> students) {
        students.stream().map(x -> x.getName()+"님").forEach(System.out::println);
    }

    private void collectName(List<StudentDTO> students) {
        List<String> nameList = students.stream().map(x -> x.getName()).collect(Collectors.toList());
        System.out.println(nameList);
    }

    private void filterAgradeMath(List<StudentDTO> students, int scoreCutline) {
        students.stream()
            .filter(student -> student.getScoreMath() > scoreCutline)
            .forEach(student -> System.out.println(student.getName()));
    }

}
