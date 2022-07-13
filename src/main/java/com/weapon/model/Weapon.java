package com.weapon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Weapon {
    private Integer id;
    private String type;
    private String name;
    private Integer firingRange; //прицельная дальность
    private Integer storeCapacity; //емкость магазина
    private Integer weight;
    private Integer rateOfFire; //скорострельность
    public Weapon(){}

}