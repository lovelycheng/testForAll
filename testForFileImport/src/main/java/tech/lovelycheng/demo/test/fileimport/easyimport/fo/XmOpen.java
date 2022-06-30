package tech.lovelycheng.demo.test.fileimport.easyimport.fo;

import lombok.Data;

/**
* @author chengtong
* @date 2022/6/28 15:07
*/
@Data
@Table("open")
public class XmOpen extends Archived{

    /**
     * 账务日期
     */
    @Column(name = "cur_date")
    private String curDate;
    /**
     * 牵头方
     */
    @Column(name = "leader")
    private String leader;
    /**
     * 合作方
     */
    @Column(name = "partner")
    private String partner;
    /**
     * 客户姓名
     */
    @Column(name = "cust_name")
    private String custName;
    /**
     * 证件类型
     */
    @Column(name = "cert_type")
    private String certType;
    /**
     * 证件号
     */
    @Column(name = "cert_no")
    private String certNo;
    /**
     * 借据号
     */
    @Column(name = "loan_id")
    private String loanId;
    /**
     * 申请日期
     */
    @Column(name = "apply_date")
    private String applyDate;
    /**
     * 确认日期
     */
    @Column(name = "start_date")
    private String startDate;
    /**
     * 到期日期
     */
    @Column(name = "end_date")
    private String endDate;
    /**
     * 放款银行卡号
     */
    @Column(name = "bank_card_no")
    private String bankCardNo;
    /**
     * 放款交易流水
     */
    @Column(name = "seq_no")
    private String seqNo;
    /**
     * 借据金额
     */
    @Column(name = "encash_amt")
    private String encashAmt;
    /**
     * 币种
     */
    @Column(name = "currency")
    private String currency;
    /**
     * 贷款用途
     */
    @Column(name = "use_purpose")
    private String usePurpose;
    /**
     * 还款方式
     */
    @Column(name = "repay_mode")
    private String repayMode;
    /**
     * 还款周期
     */
    @Column(name = "repay_cycle")
    private String repayCycle;
    /**
     * 总期数
     */
    @Column(name = "total_terms")
    private String totalTerms;
    /**
     * 宽限期
     */
    @Column(name = "grace_day")
    private String graceDay;
    /**
     * 放款状态
     */
    @Column(name = "fund_status")
    private String fundStatus;

}
