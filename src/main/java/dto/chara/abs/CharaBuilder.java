package dto.chara.abs;

import application.model.battle.damage.mind.MindDamage;
import application.model.battle.damage.physical.AccuracyDamage;
import application.model.battle.damage.physical.PhysicalDamage;
import dto.chara.enums.EP_TYPE;
import dto.chara.enums.JOBManage.JOB;
import dto.chara.player.Archer;
import dto.chara.player.Chief;
import dto.chara.player.Priest;
import dto.chara.player.Warrior;
import dto.chara.player.Witch;
import lombok.Getter;

@Getter
public class CharaBuilder {

    protected String _Name;
    protected EP_TYPE _Type;
    protected JOB _Job;
    protected int _Id;
    protected int _Hp;
    protected int _MHp;
    protected int _Mp;
    protected int _MMp;
    protected int _Strength;
    protected int _Intelligence;
    protected int _Vitality;
    protected int _Speed;
    protected int _Accuracy;
    protected int _Luck;

    public CharaBuilder() {}

    public CharaBuilder name(String name) {
        this._Name = name;
        return this;
    }

    public CharaBuilder type(EP_TYPE type) {
        this._Type = type;
        return this;
    }

    public CharaBuilder job(JOB job) {
        this._Job = job;
        return this;
    }

    public CharaBuilder id(int id) {
        this._Id = id;
        return this;
    }

    public CharaBuilder hp(int hp) {
        this._Hp = hp;
        return this;
    }

    public CharaBuilder mhp(int hp) {
        this._MHp = hp;
        return this;
    }

    public CharaBuilder mp(int mp) {
        this._Mp = mp;
        return this;
    }

    public CharaBuilder mmp(int mp) {
        this._MMp = mp;
        return this;
    }

    public CharaBuilder strn(int strength) {
        this._Strength = strength;
        return this;
    }

    public CharaBuilder intl(int inte) {
        this._Intelligence = inte;
        return this;
    }

    public CharaBuilder vitl(int vit) {
        this._Vitality = vit;
        return this;
    }

    public CharaBuilder sped(int speed) {
        this._Speed = speed;
        return this;
    }

    public CharaBuilder accr(int accuracy) {
        this._Accuracy = accuracy;
        return this;
    }

    public CharaBuilder luck(int luck) {
        this._Luck = luck;
        return this;
    }

    public Warrior buildWarrior() {
        return new Warrior(_Name, _Type, _Job, _Id, _Hp, _MHp, _Mp, _MMp, _Strength, _Intelligence,
                _Vitality, _Speed, _Accuracy, _Luck, new PhysicalDamage());
    }

    public Priest buildPriest() {
        return new Priest(_Name, _Type, _Job, _Id, _Hp, _MHp, _Mp, _MMp, _Strength, _Intelligence,
                _Vitality, _Speed, _Accuracy, _Luck, new MindDamage());
    }

    public Archer buildArcher() {
        return new Archer(_Name, _Type, _Job, _Id, _Hp, _MHp, _Mp, _MMp, _Strength, _Intelligence,
                _Vitality, _Speed, _Accuracy, _Luck, new AccuracyDamage());
    }

    public Witch buildWitch() {
        return new Witch(_Name, _Type, _Job, _Id, _Hp, _MHp, _Mp, _MMp, _Strength, _Intelligence,
                _Vitality, _Speed, _Accuracy, _Luck, new MindDamage());
    }

    public Chief buildChief() {
        return new Chief(_Name, _Type, _Job, _Id, _Hp, _MHp, _Mp, _MMp, _Strength, _Intelligence,
                _Vitality, _Speed, _Accuracy, _Luck, new PhysicalDamage());
    }

}
