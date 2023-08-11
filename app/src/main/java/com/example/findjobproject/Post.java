package com.example.findjobproject;

import androidx.annotation.NonNull;

public class Post {
    private final String name;
    private final String type;
    private final String gender;
    private final String description;
    private final String age;
    private final String jobPosition;
    private final String workerNationality;
    private final String whatsappNumber;

    public Post(String name, String type, String gender, String age,
                String des, String jobPosition, String workerNationality, String whatsappNumber) {
        this.name = name;
        this.type = type;
        this.gender = gender;
        this.age = age;
        this.description = des;
        this.jobPosition = jobPosition;
        this.workerNationality = workerNationality;
        this.whatsappNumber = whatsappNumber;
    }

    public String getWorkerNationality(){
        return workerNationality;
    }

    @NonNull
    @Override
    public String toString() {
        if (!workerNationality.equals("")) {
            return "Job Name: " + name +
                    "\nJob Type: " + type +
                    "\nGender: " + gender +
                    "\nAge: " + age +
                    "\nJob Position: " + jobPosition +
                    "\nWorker Nationality: " + workerNationality +
                    "\nAbout Job: " + description +
                    "\nFor Contact: " + whatsappNumber;
        }

        else{
            return  "Job Name: " + name +
                    "\nJob Type: " + type +
                    "\nGender: " + gender +
                    "\nAge: " + age +
                    "\nJob Position: " + jobPosition +
                    "\nWorker Nationality: For all" +
                    "\nAbout Job: " + description +
                    "\nFor Contact: " + whatsappNumber;
        }
    }
}