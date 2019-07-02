package jp.abc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class MsgData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @NotNull
    private Long id;

    @Column
    private String title;

    @Column
    @NotEmpty
    private String message;

    @ManyToOne
    private MyData mydata;

    public MsgData() {
        super();
        mydata = new MyData();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MyData getMydata() {
        return mydata;
    }

    public void setMydata(MyData mydata) {
        this.mydata = mydata;
    }

}