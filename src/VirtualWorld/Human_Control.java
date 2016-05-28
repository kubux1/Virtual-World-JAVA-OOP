package VirtualWorld;

/**
 * Created by Kuba on 2016-05-09.
 */
public class Human_Control  implements java.io.Serializable {
    int moveX_;
    int moveY_;
    boolean humanSuperPower_;
    int duration_;
    int cooldown_;
    boolean used_;

    Human_Control() {
        this.moveX_ = 0;
        this.moveY_ = 0;
        this.humanSuperPower_ = false;
        this.duration_ = 0;
        this.cooldown_ = 5;
        this.used_ = false;
    }
    int getMoveX() {
        return this.moveX_;
    }
    int getMoveY() {
        return this.moveY_;
    }
    void setMoveX(int moveX) {
        this.moveX_ = moveX;
    }
    void setMoveY(int moveY) {
        this.moveY_ = moveY;
    }
    void deleteCoordinates() {
        this.moveX_ = 0;
        this.moveY_ = 0;
    }

    boolean setHumanSuperPower() {
        if (this.cooldown_ == 5 && this.duration_ == 0 && used_ == false) {
            this.duration_ = 5;
            return true;
        }
        else
        return false;
    }
    boolean durationPowerStatus() {
        if (this.cooldown_ == 5 && this.duration_ > 0)
        return true;
        else return false;
    }
    boolean cooldownPowerStatus() {
        if (used_ == true)
            return true;
        else return false;
    }
    void decreaseSuperPowerDuration() {
        if (this.duration_ > 0)
        this.duration_ -= 1;
        if(this.duration_ == 0)
        used_ = true;
    }
    void decreaseSuperPowerCooldown() {
        if (this.duration_ == 0 && this.cooldown_ > 0)
        this.cooldown_ -= 1;
        else {
            cooldown_ = 5;
            used_ = false;
        }
    }
    int getDuration() {
        return this.duration_;
    }
    int getCooldwon() {
        return this.cooldown_;
    }
    boolean getUsed() {
        return this.used_;
    }
    void setControls(int duration, int cooldown , boolean used) {
        this.duration_ = duration;
        this.cooldown_ = cooldown;
        this.used_ = used;
    }

}
