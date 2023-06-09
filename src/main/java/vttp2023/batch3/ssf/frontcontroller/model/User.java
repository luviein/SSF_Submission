package vttp2023.batch3.ssf.frontcontroller.model;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.validation.constraints.Size;

public class User implements Serializable {

    @Size(min = 2, message = "Username must be at least 2 characters.")
    private String username;

    @Size(min = 2, message = "Password must be at least 2 characters")
    private String password;

    private boolean isAuthenticated = false;

    private int wrongCount = 0;

    private int answer;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public int getWrongCount() {
        return wrongCount;
    }

    public void setWrongCount(int wrongCount) {
        this.wrongCount = wrongCount;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + ", isAuthenticated=" + isAuthenticated
                + ", wrongCount=" + wrongCount + "]";
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("username", this.getUsername())
                .add("password", this.getPassword())

                .build();
    }

    public static JsonObject toJSON(String json) {
        JsonReader r = (JsonReader) Json.createReader(new StringReader(json));
        return r.readObject();
    }

    public static final String[] OPERANDS = {
		"+",
		"-",
		"/",
		"*",
	};

    public  String generateCaptcha(){
		List<String> captchaCode = new ArrayList<>();
		Random random = new Random();
		int min = 1;
		int max = 50;
		int a = (int)(Math.random()*(max-min+1)+min);  
		int b = (int)(Math.random()*(max-min+1)+min); 
        int index = random.nextInt(OPERANDS.length);
        String operand = OPERANDS[index];
        int ans = 0;
        switch(operand){
            case "+":
                ans = a + b;
                break;
            case "-":
                ans = a - b;
                break;
            case "/":
                ans = a / b;
                break;
            case "*":
                ans = a * b;
                break;
            default:
                System.out.println("Please input a value");
        }
        setAnswer(ans);

		captchaCode.add(Integer.toString(a));
		captchaCode.add(Integer.toString(b));
		
		StringBuffer sb = new StringBuffer();
		sb.append(captchaCode.get(0));
		sb.append(operand);
		sb.append(captchaCode.get(1));

        
       
        
		return(sb.toString());
	}


    // public static User retrieveJSON(String json) throws IOException{
    // try
    // }

}
