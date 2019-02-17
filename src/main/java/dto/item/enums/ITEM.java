package dto.item.enums;

import dto.chara.abs.Characters;
import dto.chara.enums.JOBManage.JOB;
import lombok.Getter;

public enum ITEM {
    // 解放：初回外出時
    MEDHICAL("EXPL_ITEM_", "薬草", "pict/item/medhical.png"), //
    PUCHIRING("EXPL_ITEM_", "指輪", "pict/item/puchiring.png"), //
    FLAG("EXPL_ITEM_", "退散", "pict/item/flag.png"), //

    // 解放：初回討伐時
    BILL("EXPL_ITEM_", "お札", "pict/item/bill.png"), //
    BOOK("EXPL_ITEM_", "スキルブック", "pict/item/book.png"), //
    BOOTS("EXPL_ITEM_", "革ブーツ", "pict/item/boots.png"), //
    SHIELD("EXPL_ITEM_", "革シールド", "pict/item/shield.png"), //

    // 解放：10階層到達時
    KEY("EXPL_ITEM_", "銀のカギ", "pict/item/key.png"), //
    FOX("EXPL_ITEM_", "狐の面", "pict/item/fox.png"), //

    // 解放：20階層到達時
    CAPE("EXPL_ITEM_", "ローブ", "pict/item/cape.png"), //
    WITCHHAT("EXPL_ITEM_", "ウィッチハット", "pict/item/witchhat.png"), //
    ARMOR("EXPL_ITEM_", "プレートアーマー", "pict/item/armor.png"), //

    // 解放：30階層到達時
    TWOSWORD("EXPL_ITEM_", "二刀", "pict/item/twosword.png"), //
    HUENKEL("EXPL_ITEM_", "ヒュンケル", "pict/item/huenkel.png"), //
    WAND("EXPL_ITEM_", "シャドウスタッフ", "pict/item/wand.png"), //

    // 解放：40階層到達時
    AXS("EXPL_ITEM_", "ハートブレイカー", "pict/item/axs.png"), //
    EXS("EXPL_ITEM_", "エクスカリバー", "pict/item/exs.png"), //

    // 解放：全滅時
    RIBON("EXPL_ITEM_", "リボン", "pict/item/ribon.png"), //
    LIFEGAIN("EXPL_ITEM_", "ライフストリーム", "pict/item/lifegain.png"), //

    // 解放：5回外出時
    CHRONICLES("EXPL_ITEM_", "とき時計", "pict/item/chronicles.png"),; //

    private final String id;
    @Getter
    private final String name;
    @Getter
    private final String path;

    private ITEM(String id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    public String getPropId() {
        return this.id + makeNumber();
    }

    private String makeNumber() {
        int order = this.ordinal();
        if (order > 99) {
            return "0" + order;
        } else if (order > 9) {
            return "00" + order;
        } else {
            return String.valueOf(order);
        }
    }

    public static ITEM getEnum(int id) {
        for (ITEM item : ITEM.values()) {
            if (item.ordinal() == id) {
                return item;
            }
        }
        throw new IllegalArgumentException("入力値不正[" + id + "]");
    }

    public void affect(Characters chr) {
        switch (this) {
            case MEDHICAL:
                System.out.println("ITEM.affect[" + this + "]なので何もしない");
                break;
            case PUCHIRING:
                System.out.println("ITEM.affect[" + this + "]なので何もしない");
                break;
            case FLAG:
                System.out.println("ITEM.affect[" + this + "]なので何もしない");
                break;
            case BILL:
                System.out.println("ITEM.affect[" + this + "]なので何もしない");
                break;
            case BOOK:
                System.out.println("ITEM.affect[" + this + "]なので何もしない");
                break;
            case BOOTS:
                upgradeSpeed(1.1, chr);
                break;
            case SHIELD:
                upgradeDeffence(1.2, chr);
                break;
            case KEY:
                System.out.println("ITEM.affect[" + this + "]なので何もしない");
                break;
            case FOX:
                upgradeLuck(1.25, chr);
                break;
            case CAPE:
                upgradeDeffence(1.1, chr);
                upgradeInt(1.2, chr);
                break;
            case WITCHHAT:
                upgradeInt(1.5, chr, JOB.WITCH_F);
                break;
            case ARMOR:
                upgradeDeffence(1.5, chr);
                break;
            case TWOSWORD:
                upgradeAtk(1.5, chr, JOB.CHIEF_M);
                upgradeSpeed(1.5, chr, JOB.CHIEF_M);
                break;
            case HUENKEL:
                upgradeAtk(1.5, chr, JOB.ARCHER_F);
                upgradeAccuracy(1.5, chr, JOB.ARCHER_F);
                break;
            case WAND:
                upgradeInt(2.0, chr, JOB.WITCH_F);
                break;
            case AXS:
                upgradeAtk(2.0, chr, JOB.WARRIOR_F, JOB.WARRIOR_M);
                break;
            case EXS:
                upgradeAtk(1.5, chr, JOB.WARRIOR_F, JOB.WARRIOR_M);
                upgradeInt(1.5, chr, JOB.WARRIOR_F, JOB.WARRIOR_M);
                break;
            case RIBON:
                System.out.println("ITEM.affect[" + this + "]なので何もしない");
                break;
            case LIFEGAIN:
                System.out.println("ITEM.affect[" + this + "]なので何もしない");
                break;
            case CHRONICLES:
                System.out.println("ITEM.affect[" + this + "]なので何もしない");
                break;
            default:
                break;
        }
    }

    private void upgradeInt(double bias, Characters chr, JOB... jobEnums) {
        if (jobEnums == null) {
            System.out.println("[" + this + "] upgrade[" + chr.get_Job() + "] int["
                    + chr.get_Intelligence() + "]");
            chr.set_Intelligence((int) (chr.get_Intelligence() * bias));
            System.out.println("[" + this + "] upgrade[" + chr.get_Job() + "] int["
                    + chr.get_Intelligence() + "]");
            return;
        }

        for (JOB job : jobEnums) {
            if (chr.get_Job() == job) {
                System.out.println("[" + this + "] upgrade[" + chr.get_Job() + "] int["
                        + chr.get_Intelligence() + "]");
                chr.set_Intelligence((int) (chr.get_Intelligence() * bias));
                System.out.println("[" + this + "] upgrade[" + chr.get_Job() + "] int["
                        + chr.get_Intelligence() + "]");
                return;
            }
        }
    }

    private void upgradeLuck(double bias, Characters chr, JOB... jobEnums) {
        System.out.println(
                "[" + this + "] upgrade[" + chr.get_Job() + "] luck[" + chr.get_Luck() + "]");
        chr.set_Luck((int) (chr.get_Luck() * bias));
        System.out.println(
                "[" + this + "] upgrade[" + chr.get_Job() + "] luck[" + chr.get_Luck() + "]");
    }

    private void upgradeSpeed(double bias, Characters chr, JOB... jobEnums) {
        if (jobEnums == null) {
            System.out.println(
                    "[" + this + "] upgrade[" + chr.get_Job() + "] speed[" + chr.get_Speed() + "]");
            chr.set_Speed((int) (chr.get_Speed() * bias));
            System.out.println(
                    "[" + this + "] upgrade[" + chr.get_Job() + "] speed[" + chr.get_Speed() + "]");
            return;
        }

        for (JOB job : jobEnums) {
            if (chr.get_Job() == job) {
                System.out.println("[" + this + "] upgrade[" + chr.get_Job() + "] speed["
                        + chr.get_Speed() + "]");
                chr.set_Speed((int) (chr.get_Speed() * bias));
                System.out.println("[" + this + "] upgrade[" + chr.get_Job() + "] speed["
                        + chr.get_Speed() + "]");
                return;
            }
        }
    }

    private void upgradeAtk(double bias, Characters chr, JOB... jobEnums) {
        for (JOB job : jobEnums) {
            if (chr.get_Job() == job) {
                System.out.println("[" + this + "] upgrade[" + chr.get_Job() + "] atk["
                        + chr.get_Strength() + "]");
                chr.set_Strength((int) (chr.get_Strength() * bias));
                System.out.println("[" + this + "] upgrade[" + chr.get_Job() + "] atk["
                        + chr.get_Strength() + "]");
                return;
            }
        }
    }

    private void upgradeAccuracy(double bias, Characters chr, JOB... jobEnums) {
        for (JOB job : jobEnums) {
            if (chr.get_Job() == job) {
                System.out.println("[" + this + "] upgrade[" + chr.get_Job() + "] acc["
                        + chr.get_Accuracy() + "]");
                chr.set_Accuracy((int) (chr.get_Accuracy() * bias));
                System.out.println("[" + this + "] upgrade[" + chr.get_Job() + "] acc["
                        + chr.get_Accuracy() + "]");
                return;
            }
        }
    }

    private void upgradeDeffence(double bias, Characters chr, JOB... jobEnums) {
        System.out.println(
                "[" + this + "] upgrade[" + chr.get_Job() + "] vit[" + chr.get_Vitality() + "]");
        chr.set_Vitality((int) (chr.get_Vitality() * bias));
        System.out.println(
                "[" + this + "] upgrade[" + chr.get_Job() + "] vit[" + chr.get_Vitality() + "]");
    }
}
