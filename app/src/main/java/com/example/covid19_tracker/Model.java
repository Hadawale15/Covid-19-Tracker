package com.example.covid19_tracker;

public class Model {
    String flag,country,population,cases,todaycases,deaths,todaysdeaths,recovered,todaysrecovered,active,critical;

    public Model() {
    }

    public Model(String flag, String country, String population, String cases, String todaycases, String deaths, String todaysdeaths, String recovered, String todaysrecovered, String active, String critical) {
        this.flag = flag;
        this.country = country;
        this.population = population;
        this.cases = cases;
        this.todaycases = todaycases;
        this.deaths = deaths;
        this.todaysdeaths = todaysdeaths;
        this.recovered = recovered;
        this.todaysrecovered = todaysrecovered;
        this.active = active;
        this.critical = critical;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodaycases() {
        return todaycases;
    }

    public void setTodaycases(String todaycases) {
        this.todaycases = todaycases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodaysdeaths() {
        return todaysdeaths;
    }

    public void setTodaysdeaths(String todaysdeaths) {
        this.todaysdeaths = todaysdeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getTodaysrecovered() {
        return todaysrecovered;
    }

    public void setTodaysrecovered(String todaysrecovered) {
        this.todaysrecovered = todaysrecovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }
}
