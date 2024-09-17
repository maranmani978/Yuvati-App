package com.murugamani.example.yuvati.data;

/**
 * Created by Murugamani on 3/18/2018.
 */

public class StateCrime {

    private String siNo,name,incidence,percentage,population,total,rank;

    public StateCrime(){}

    public StateCrime(String no, String names, String incidences, String percent, String populations, String totals, String ranks){
        this.siNo = no;
        this.name = names;
        this.incidence = incidences;
        this.percentage = percent;
        this.population = populations;
        this.total = totals;
        this.rank = ranks;
    }

    public String getSiNo(){ return siNo; }
    public String getName(){ return name; }
    public String getIncidence(){ return incidence; }
    public String getPercentage(){ return percentage; }
    public String getPopulation(){ return population; }
    public String getTotal(){ return total; }
    public String getRank(){ return rank; }


}
