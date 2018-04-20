package com.qk.party.bean;

import java.util.List;

/**
 * @package： com.qk.party.bean
 * @class: TaskDetailsBean
 * @author:  小飞
 * @date: 2017/10/31 14:35
 * @描述：
 */

public class TaskDetailsBean {

    private String leader;
    private int sfgk;
    private String flag;
    private int rwlx;
    private String create_time;
    private String complete_at;
    private String t_content;
    private int emergency;
    private String dept;
    private String demand;
    private String t_source;
    private String ref_num;
    private String t_name;
    private int task_type;
    private String jjcdString;
    private List<Attach> attachList;
    private List<Rwfj> rwfjList;
    private List<Rwdb> rwdbList;
    private List<Rwhy> rwhyList;
    private List<Rwjl> rwjlList;

    private List<OListBean> oList;

    public String getJjcdString() {
        return jjcdString;
    }

    public void setJjcdString(String jjcdString) {
        this.jjcdString = jjcdString;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public int getSfgk() {
        return sfgk;
    }

    public void setSfgk(int sfgk) {
        this.sfgk = sfgk;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getRwlx() {
        return rwlx;
    }

    public void setRwlx(int rwlx) {
        this.rwlx = rwlx;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getComplete_at() {
        return complete_at;
    }

    public void setComplete_at(String complete_at) {
        this.complete_at = complete_at;
    }

    public String getT_content() {
        return t_content;
    }

    public void setT_content(String t_content) {
        this.t_content = t_content;
    }

    public int getEmergency() {
        return emergency;
    }

    public void setEmergency(int emergency) {
        this.emergency = emergency;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public String getT_source() {
        return t_source;
    }

    public void setT_source(String t_source) {
        this.t_source = t_source;
    }

    public String getRef_num() {
        return ref_num;
    }

    public void setRef_num(String ref_num) {
        this.ref_num = ref_num;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public int getTask_type() {
        return task_type;
    }

    public void setTask_type(int task_type) {
        this.task_type = task_type;
    }
    public List<Attach> getAttachList() {
        return attachList;
    }

    public void setAttachList(List<Attach> attachList) {
        this.attachList = attachList;
    }

    public List<Rwfj> getRwfjList() {
        return rwfjList;
    }

    public void setRwfjList(List<Rwfj> rwfjList) {
        this.rwfjList = rwfjList;
    }

    public List<Rwdb> getRwdbList() {
        return rwdbList;
    }

    public void setRwdbList(List<Rwdb> rwdbList) {
        this.rwdbList = rwdbList;
    }

    public List<Rwhy> getRwhyList() {
        return rwhyList;
    }

    public void setRwhyList(List<Rwhy> rwhyList) {
        this.rwhyList = rwhyList;
    }

    public List<Rwjl> getRwjlList() {
        return rwjlList;
    }

    public void setRwjlList(List<Rwjl> rwjlList) {
        this.rwjlList = rwjlList;
    }

    public List<OListBean> getOList() {
        return oList;
    }

    public void setOList(List<OListBean> oList) {
        this.oList = oList;
    }





    public static class OListBean {

        /**
         * sbr : 郭明明
         * fj :
         * jznr : <p>“两学一做”学习常态化学习教育会议</p><p>会议地点：会议室</p><p>参会人员：梁振明、郭明明、吕志强</p><p>梁振明同志主持召开，会议传达上级文件精神，及学习要求。</p><p>一、传达文件精神</p><p>1、提高思想认识，明确目标要求。</p><p>2、注重学习重点，在学上深化拓展。</p><p>3、坚持学做相结合，在做上深化拓展。</p><p>4、领导带头，在常上深化拓展。</p><p>5、加强组织领导，在实上下功夫。</p><p>学习要求</p><p>“三会一课”在今后要学做结合及常态化学习教育，提高认识，目的明确。</p><p style="text-align: center;"><img src="http://101.200.35.180:8080//upload/image/20170821/1503278476790095618.png" title="1503278476790095618.png" alt="党建.png"/></p>
         * created_at : 2017-08-21 09:21:45
         * task_id : 643
         * attachList : []
         * wcjd : 100
         * sbsj : 2017-08-21 09:21:45
         * is_last : 1
         * sbdw : 黄龙县汇森林业综合开发有限责任公司党支部
         * zt : 1
         * id : 40
         * rwshList : [{"shlx":1,"sjshyj":"通过","shld":"农工部","created_at":1503537196000,"task_id":40,"id":216}]
         */

        private String sbr;
        private String fj;
        private String jznr;
        private String created_at;
        private int task_id;
        private String wcjd;
        private String sbsj;
        private String is_last;
        private String sbdw;
        private String zt;
        private int id;
        private List<Attach> attachList;
        /**
         * shlx : 1
         * sjshyj : 通过
         * shld : 农工部
         * created_at : 1503537196000
         * task_id : 40
         * id : 216
         */

        private List<RwshListBean> rwshList;

        public String getSbr() {
            return sbr;
        }

        public void setSbr(String sbr) {
            this.sbr = sbr;
        }

        public String getFj() {
            return fj;
        }

        public void setFj(String fj) {
            this.fj = fj;
        }

        public String getJznr() {
            return jznr;
        }

        public void setJznr(String jznr) {
            this.jznr = jznr;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getTask_id() {
            return task_id;
        }

        public void setTask_id(int task_id) {
            this.task_id = task_id;
        }

        public String getWcjd() {
            return wcjd;
        }

        public void setWcjd(String wcjd) {
            this.wcjd = wcjd;
        }

        public String getSbsj() {
            return sbsj;
        }

        public void setSbsj(String sbsj) {
            this.sbsj = sbsj;
        }

        public String getIs_last() {
            return is_last;
        }

        public void setIs_last(String is_last) {
            this.is_last = is_last;
        }

        public String getSbdw() {
            return sbdw;
        }

        public void setSbdw(String sbdw) {
            this.sbdw = sbdw;
        }

        public String getZt() {
            return zt;
        }

        public void setZt(String zt) {
            this.zt = zt;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<Attach> getAttachList() {
            return attachList;
        }

        public void setAttachList(List<Attach> attachList) {
            this.attachList = attachList;
        }

        public List<RwshListBean> getRwshList() {
            return rwshList;
        }

        public void setRwshList(List<RwshListBean> rwshList) {
            this.rwshList = rwshList;
        }
    }
    public static class RwshListBean {
        private int shlx;
        private String sjshyj;
        private String shld;
        private long created_at;
        private int task_id;
        private int id;

        public int getShlx() {
            return shlx;
        }

        public void setShlx(int shlx) {
            this.shlx = shlx;
        }

        public String getSjshyj() {
            return sjshyj;
        }

        public void setSjshyj(String sjshyj) {
            this.sjshyj = sjshyj;
        }

        public String getShld() {
            return shld;
        }

        public void setShld(String shld) {
            this.shld = shld;
        }

        public long getCreated_at() {
            return created_at;
        }

        public void setCreated_at(long created_at) {
            this.created_at = created_at;
        }

        public int getTask_id() {
            return task_id;
        }

        public void setTask_id(int task_id) {
            this.task_id = task_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
    public static class Attach{

        private String displayPath;
        private String name;
        private String originName;
        public String getDisplayPath() {
            return displayPath;
        }

        public void setDisplayPath(String displayPath) {
            this.displayPath = displayPath;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOriginName() {
            return originName;
        }

        public void setOriginName(String originName) {
            this.originName = originName;
        }
    }
    public static class Rwfj{

        /**
         * receiver : 石堡镇党委
         * wcsx : 2017-11-04
         * statusString : 已下发
         * groupId : 130
         * child_task_id : 488
         * task_content : 颠三倒四
         * report_date : 2017-11-04
         * status : 1
         */

        private String receiver;
        private String wcsx;
        private String statusString;
        private int groupId;
        private int child_task_id;
        private String task_content;
        private String report_date;
        private int status;

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getWcsx() {
            return wcsx;
        }

        public void setWcsx(String wcsx) {
            this.wcsx = wcsx;
        }

        public String getStatusString() {
            return statusString;
        }

        public void setStatusString(String statusString) {
            this.statusString = statusString;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public int getChild_task_id() {
            return child_task_id;
        }

        public void setChild_task_id(int child_task_id) {
            this.child_task_id = child_task_id;
        }

        public String getTask_content() {
            return task_content;
        }

        public void setTask_content(String task_content) {
            this.task_content = task_content;
        }

        public String getReport_date() {
            return report_date;
        }

        public void setReport_date(String report_date) {
            this.report_date = report_date;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
    public static class Rwdb{
        private String ck_date;
        private String ck_member;
        private String ck_leader;
        private String content;
        private String result;
        private String feedback;

        public String getCk_date() {
            return ck_date;
        }

        public void setCk_date(String ck_date) {
            this.ck_date = ck_date;
        }

        public String getCk_member() {
            return ck_member;
        }

        public void setCk_member(String ck_member) {
            this.ck_member = ck_member;
        }

        public String getCk_leader() {
            return ck_leader;
        }

        public void setCk_leader(String ck_leader) {
            this.ck_leader = ck_leader;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getFeedback() {
            return feedback;
        }

        public void setFeedback(String feedback) {
            this.feedback = feedback;
        }
    }
    public static class Rwhy{
        private String subject;
        private String typeString;
        private String presenter;
        private String meeting_date;
        private String user_name;
        private String address;
        private String content;
        private List<Users> users;

        public List<Users> getUsers() {
            return users;
        }

        public void setUsers(List<Users> users) {
            this.users = users;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getTypeString() {
            return typeString;
        }

        public void setTypeString(String typeString) {
            this.typeString = typeString;
        }

        public String getPresenter() {
            return presenter;
        }

        public void setPresenter(String presenter) {
            this.presenter = presenter;
        }

        public String getMeeting_date() {
            return meeting_date;
        }

        public void setMeeting_date(String meeting_date) {
            this.meeting_date = meeting_date;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
    public static class Rwjl {
        private String create_time;
        private String content;
        private String name;

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    public static class Users{
        private String xm;

        public String getXm() {
            return xm;
        }

        public void setXm(String xm) {
            this.xm = xm;
        }
    }
}
