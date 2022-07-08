package model;

public class Weapon {
    public Integer id;
    public String type;
    public String name;
    public Integer firingRange; //прицельная дальность
    public Integer storeCapacity; //емкость магазина
    public Integer weight;
    public Integer rateOfFire; //скорострельность

    @Override
    public String toString() {
        return "Weapon{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", firingRange=" + firingRange +
                ", storeCapacity=" + storeCapacity +
                ", weight=" + weight +
                ", rateOfFire=" + rateOfFire +
                '}';
    }

    public Weapon(Integer id, String type, String name, Integer firingRange, Integer storeCapacity, Integer weight, Integer rateOfFire) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.firingRange = firingRange;
        this.storeCapacity = storeCapacity;
        this.weight = weight;
        this.rateOfFire = rateOfFire;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id){
        this.id=id;
    }

    public String getType() {
        return type;
    }
    public void setType(String type){
        this.type=type;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public Integer getFiringRange() {
        return firingRange;
    }
    public void setFiringRange(Integer firingRange){
        this.firingRange=firingRange;
    }

    public Integer getStoreCapacity(){
        return storeCapacity;
    }
    public void setStoreCapacity(Integer storeCapacity){
        this.storeCapacity=storeCapacity;
    }

    public Integer getWeight(){
        return weight;
    }
    public void setWeight(Integer weight){
        this.weight=weight;
    }

    public Integer getRateOfFire(){
        return rateOfFire;
    }

    public void setRateOfFire(Integer rateOfFire) {
        this.rateOfFire = rateOfFire;
    }
}
