package cloudPayAdmin.admin.entity.http;

/**
 * Created by chenjiajian on 2017/8/11.
 */
public class CloudPayBlackCard {
	
    private String yct_no;
    private long opt_type;//0 删除，1 增加
    private long id;

    public String getYct_no() {
        return yct_no;
    }

    public void setYct_no(String yct_no) {
        this.yct_no = yct_no;
    }

    public long getOpt_type() {
        return opt_type;
    }

    public void setOpt_type(long opt_type) {
        this.opt_type = opt_type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
