package example.extralib.jackson;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonExample {

    public static void main(String[] args) {
        JacksonExample obj = new JacksonExample();
        obj.run();
    }

    private void run() {
        ObjectMapper mapper = new ObjectMapper();

        Staff staff = createDummyObject();

        try {
            // object -> json -> file
            mapper.writeValue(new File("D:\\staff.json"), staff);

            // object -> json
            String jsonInString = mapper.writeValueAsString(staff);
            System.out.println(jsonInString);

            // file -> json -> object
            Staff staff2 = mapper.readValue(new File("D:\\staff.json"), Staff.class);
            System.out.println(staff2);

            // json -> object
            String jsonInString2 = "{\"name\":\"mkyong\",\"salary\":7500,\"skills\":[\"java\",\"python\"]}";
            Staff staff3 = mapper.readValue(jsonInString2, Staff.class);  // 缺失的字段为null
            System.out.println(staff3);

            // object -> pretty json
            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(staff);
            System.out.println(jsonInString);

            // normal下看不到salary, 相当于json转换时, 不计算salary字段
            System.out.println("Normal View");
            String normalView = mapper.writerWithView(Views.Normal.class).writeValueAsString(staff);
            System.out.println(normalView);

            String jsonInString3 = "{\"name\":\"mkyong\",\"age\":33,\"position\":\"Developer\",\"salary\":7500,\"skills\":[\"java\",\"python\"]}";
            Staff normalStaff = mapper.readerWithView(Views.Normal.class).forType(Staff.class).readValue(jsonInString3);
            System.out.println(normalStaff);

            // manager下能看到所有
            System.out.println("\nManager View");
            String managerView = mapper.writerWithView(Views.Manager.class).writeValueAsString(staff);
            System.out.println(managerView);

            Staff managerStaff = mapper.readerWithView(Views.Manager.class).forType(Staff.class).readValue(jsonInString3);
            System.out.println(managerStaff);

            // json array -> List
            String json = "[{\"name\":\"mkyong\"}, {\"name\":\"laplap\"}]";
            List<Staff> list = mapper.readValue(json, new TypeReference<List<Staff>>(){});
            System.out.println(list);

            // json -> Map
            String json2 = "{\"name\":\"mkyong\", \"age\":33}";
            Map<String, Object> map = mapper.readValue(json2, new TypeReference<Map<String,Object>>(){});
            System.out.println(map);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成Staff对象
     * @return
     */
    private Staff createDummyObject() {
        Staff staff = new Staff();
        staff.setName("mkyong");
        staff.setAge(33);
        staff.setPosition("Developer");
        staff.setSalary(new BigDecimal("7500"));
        List<String> skills = new ArrayList<>();
        skills.add("java");
        skills.add("python");
        staff.setSkills(skills);

        return staff;
    }

}
