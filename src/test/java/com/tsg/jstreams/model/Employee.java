package com.tsg.jstreams.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Employee implements Comparable<Employee>{

    private String id;
    private int accessKey;
    private String careerChampionId;
    private String address;
    private int defaultAddress;
    private LocalDateTime lastUpdated;

    public Employee(String id, int accessKey, String careerChampionId
            , String address, int defaultAddress, LocalDateTime lastUpdated){
        this.id = id;
        this.accessKey = accessKey;
        this.careerChampionId = careerChampionId;
        this.address = address;
        this.defaultAddress = defaultAddress;
        this.lastUpdated = lastUpdated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(int accessKey) {
        this.accessKey = accessKey;
    }

    public String getCareerChampionId() {
        return careerChampionId;
    }

    public void setCareerChampionId(String careerChampionId) {
        this.careerChampionId = careerChampionId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(int defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString(){
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return this.id + "|" + this.accessKey + "|" + this.careerChampionId + "|"+ this.address
                + "|" + this.defaultAddress + "|" + this.lastUpdated.format(dateTimeFormatter)+ "|";
    }

    @Override
    public int compareTo(Employee o) {
        return this.accessKey - o.accessKey;
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder()
                .append(this.id)
                .append(this.accessKey)
                .append(this.careerChampionId)
                .toHashCode();
    }

    @Override
    public boolean equals(final Object otherObj){
        if(otherObj instanceof Employee){
            final Employee otherEmployee = (Employee) otherObj;
            return new EqualsBuilder()
                    .append(this.id, otherEmployee.id)
                    .append(this.accessKey, otherEmployee.accessKey)
                    .append(this.careerChampionId, otherEmployee.careerChampionId)
                    .isEquals();
        } else{
            return false;
        }
    }

    public GroupingCombination getGroupingCombination(){
        return new GroupingCombination(this.id, this.accessKey, this.careerChampionId);
    }

    public String getGroupingCombinationString(){
        return new StringBuilder().append(this.id).append(this.accessKey).append(this.careerChampionId).toString();
    }

    public static class GroupingCombination{

        private String id;
        private int accessKey;
        private String careerChampionId;

        public GroupingCombination(String id, int accessKey, String careerChampionId){
            this.id = id;
            this.accessKey = accessKey;
            this.careerChampionId = careerChampionId;
        }

        @Override
        public String toString(){
            return this.id + "|" + this.accessKey + "|" + this.careerChampionId +"|";
        }

        @Override
        public int hashCode(){
            return new HashCodeBuilder()
                    .append(this.id)
                    .append(this.accessKey)
                    .append(this.careerChampionId)
                    .toHashCode();
        }

        @Override
        public boolean equals(final Object otherObj){
            if(otherObj instanceof Employee){
                final Employee otherEmployee = (Employee) otherObj;
                return new EqualsBuilder()
                        .append(this.id, otherEmployee.id)
                        .append(this.accessKey, otherEmployee.accessKey)
                        .append(this.careerChampionId, otherEmployee.careerChampionId)
                        .isEquals();
            } else{
                return false;
            }
        }
    }
}
