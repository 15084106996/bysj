package bean;

public class Usual {
    private String cid;
    private String cname;
    private String stid;
    private String stname;
    private Long score;
    private Long sumscore;
    private String details;
    private Long avgscore;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Long getAvgscore() {
        return avgscore;
    }

    public void setAvgscore(Long avgscore) {
        this.avgscore = avgscore;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    private String opinion;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getStid() {
        return stid;
    }

    public void setStid(String stid) {
        this.stid = stid;
    }

    public String getStname() {
        return stname;
    }

    public void setStname(String stname) {
        this.stname = stname;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getSumscore() {
        return sumscore;
    }

    public void setSumscore(Long sumscore) {
        this.sumscore = sumscore;
    }
}
