package com.murugamani.example.yuvati.data;

/**
 * Created by Murugamani on 3/20/2018.
 */

public class Incidence {

    private String name,population,rapeIncidence,rapeRate,rapePercent,kidnapIncidence,kidnapRate,kidnapPercent
            ,dowryIncidence,dowryRate,dowryPercent,relationIncidence,relationRate,relationPercent
            ,molestationIncidence,molestationRate,molestationPercent,sexualIncidence,sexualRate,sexualPercent
            ,girlsIncidence,girlsRate,girlsPercent,satiIncidence,satiRate,satiPercent
            ,immoralIncidence,immoralRate,immoralPercent,indecentIncidence,indecentRate,indecentPercent
            ,totalIncidence,totalRate,totalPercent;

    public Incidence(String names, String pop, String ri, String rr, String rp, String ki, String kr, String kp,
                     String di, String dr, String dp, String rei, String rer, String rep,
                     String mi, String mr, String mp, String si, String sr, String sp,
                     String gi, String gr, String gp, String sai, String sar, String sap,
                     String ii, String ir, String ip, String ini, String inr, String inp,
                     String ti, String tr, String tp){

        this.name = names;                 this.population = pop;
        this.rapeIncidence = ri;           this.rapeRate = rr;           this.rapePercent = rp;
        this.kidnapIncidence = ki;         this.kidnapRate = kr;         this.kidnapPercent = kp;
        this.dowryIncidence = di;          this.dowryRate = dr;          this.dowryPercent = dp;
        this.relationIncidence = rei;      this.relationRate = rer;      this.relationPercent = rep;
        this.molestationIncidence = mi;    this.molestationRate = mr;    this.molestationPercent = mp;
        this.sexualIncidence = si;         this.sexualRate = sr;         this.sexualPercent = sp;
        this.girlsIncidence = gi;          this.girlsRate = gr;          this.girlsPercent = gp;
        this.satiIncidence = sai;          this.satiRate = sar;          this.sexualPercent = sap;
        this.immoralIncidence = ii;        this.immoralRate = ir;        this.immoralPercent = ip;
        this.indecentIncidence = ini;      this.indecentRate = inr;      this.indecentPercent = inp;
        this.totalIncidence = ti;          this.totalRate = tr;          this.totalPercent = tp;
    }


    public String getName(){ return name; }
    public String getPopulation(){ return population; }
    public String getRapeIncidence(){ return rapeIncidence; }
    public String getRapeRate(){ return rapeRate; }
    public String getRapePercent(){ return rapePercent; }
    public String getKidnapIncidence(){ return kidnapIncidence; }
    public String getKidnapRate(){ return kidnapRate; }
    public String getKidnapPercent(){ return kidnapPercent; }
    public String getDowryIncidence(){ return dowryIncidence; }
    public String getDowryRate(){ return dowryRate; }
    public String getDowryPercent(){ return dowryPercent; }
    public String getRelationIncidence(){ return relationIncidence; }
    public String getRelationRate(){ return relationRate; }
    public String getRelationPercent(){ return relationPercent; }
    public String getMolestationIncidence(){ return molestationIncidence; }
    public String getMolestationRate(){ return molestationRate; }
    public String getMolestationPercent(){ return molestationPercent; }
    public String getSexualIncidence(){ return sexualIncidence; }
    public String getSexualRate(){ return sexualRate; }
    public String getSexualPercent(){ return sexualPercent; }
    public String getGirlsIncidence(){ return girlsIncidence; }
    public String getGirlsRate(){ return girlsRate; }
    public String getGirlsPercent(){ return girlsPercent; }
    public String getSatiIncidence(){ return satiIncidence; }
    public String getSatiRate(){ return satiRate; }
    public String getSatiPercent(){ return satiPercent; }
    public String getImmoralIncidence(){ return immoralIncidence; }
    public String getImmoralRate(){ return immoralRate; }
    public String getImmoralPercent(){ return immoralPercent; }
    public String getIndecentIncidence(){ return indecentIncidence; }
    public String getIndecentRate(){ return indecentRate; }
    public String getIndecentPercent(){ return indecentPercent; }
    public String getTotalIncidence(){ return totalIncidence; }
    public String getTotalRate(){ return totalRate; }
    public String getTotalPercent(){ return totalPercent; }

}
