package blog.dreamland.com.entity;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class RoleUser {
    //标识主键  ，解决邮件激活更新state  状态
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增长策略
    private Long id;

    private Long uId;

    private Long rId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }
}