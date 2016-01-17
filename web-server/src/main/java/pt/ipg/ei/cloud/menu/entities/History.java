package pt.ipg.ei.cloud.menu.entities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
public class History {

    @Id
    private Long id;
    private Date date;
    private Map<String,Object> map;
    private String entity;
    private HistoryType historyType;

    public History() {
        map = new HashMap<String, Object>();
        date = Calendar.getInstance().getTime();
    }

    public Date getDate() {
        return date;
    }

    public Long getId() {
        return id;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public String getEntity() {
        return entity;
    }

    public HistoryType getHistoryType() {
        return historyType;
    }
}
