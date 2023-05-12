package com.connect.model;

import java.util.List;

public class User {

    String username;

    String name;

    int age;

    String gender;

    String phoneNumber;

    String password;
    
    int minAgePreffered=18;
    
    int maxagePreffered=120;
    
    String genderPrefered="Everyone";
    
    String bio;
    
    String career;
    
    String whoKnowsYou;
    
    List<String> interests;

    public User(String name, String phoneNumber, String username, String password, int age, String gender) {
        this.username = username;
        this.password=password;
        this.phoneNumber=phoneNumber;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    
    
    
    
    public String getBio() {
		return bio;
	}


	public void setBio(String bio) {
		this.bio = bio;
	}

	
	public String getCareer() {
		return career;
	}

	
	public void setCareer(String career) {
		this.career = career;
	}




	public String getWhoKnowsYou() {
		return whoKnowsYou;
	}




	public void setWhoKnowsYou(String whoKnowsYou) {
		this.whoKnowsYou = whoKnowsYou;
	}




	public List<String> getInterests() {
		return interests;
	}




	public void setInterests(List<String> interests) {
		this.interests = interests;
	}




	public int getMinAgePreffered() {
		return minAgePreffered;
	}


	public void setMinAgePreffered(int minAgePreffered) {
		this.minAgePreffered = minAgePreffered;
	}


	public int getMaxagePreffered() {
		return maxagePreffered;
	}


	public void setMaxagePreffered(int maxagePreffered) {
		this.maxagePreffered = maxagePreffered;
	}


	public String getGenderPrefered() {
		return genderPrefered;
	}


	public void setGenderPrefered(String genderPrefered) {
		this.genderPrefered = genderPrefered;
	}


	public User() {
    	
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
