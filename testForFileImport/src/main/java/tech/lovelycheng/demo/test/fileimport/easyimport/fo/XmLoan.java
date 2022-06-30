package tech.lovelycheng.demo.test.fileimport.easyimport.fo;

import lombok.Data;

/**
 * @author chengtong
 * @date 2022/6/28 15:04
 */
@Data
@Table("loan")
public class XmLoan extends Archived{

    /**
     * 账务日期
     */
    @Column(name = "cur_date")
    private String curDate;
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
     * 结清日期
     */
    @Column(name = "clear_date")
    private String clearDate;
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
     * 当前期数
     */
    @Column(name = "cur_term")
    private String curTerm;
    /**
     * 还款日
     */
    @Column(name = "repay_day")
    private String repayDay;
    /**
     * 宽限期
     */
    @Column(name = "grace_day")
    private String graceDay;
    /**
     * 应还本金
     */
    @Column(name = "prin_total")
    private String prinTotal;
    /**
     * 已还本金
     */
    @Column(name = "prin_repay")
    private String prinRepay;
    /**
     * 本金余额
     */
    @Column(name = "prin_bal")
    private String prinBal;
    /**
     * 逾期本金余额
     */
    @Column(name = "ovd_prin_bal")
    private String ovdPrinBal;
    /**
     * 应还利息
     */
    @Column(name = "int_total")
    private String intTotal;
    /**
     * 已还利息
     */
    @Column(name = "int_repay")
    private String intRepay;
    /**
     * 利息余额
     */
    @Column(name = "int_bal")
    private String intBal;
    /**
     * 逾期利息余额
     */
    @Column(name = "ovd_int_bal")
    private String ovdIntBal;
    /**
     * 应还罚息
     */
    @Column(name = "pnlt_int_total")
    private String pnltIntTotal;
    /**
     * 已还罚息
     */
    @Column(name = "pnlt_int_repay")
    private String pnltIntRepay;
    /**
     * 罚息余额
     */
    @Column(name = "pnlt_int_bal")
    private String pnltIntBal;
    /**
     * 应还复息
     */
    @Column(name = "int_pnlt_total")
    private String intPnltTotal;
    /**
     * 已还复息
     */
    @Column(name = "int_pnlt_repay")
    private String intPnltRepay;
    /**
     * 复息余额
     */
    @Column(name = "int_pnlt_bal")
    private String intPnltBal;
    /**
     * 当天借当天还利息
     */
    @Column(name = "int_today")
    private String intToday;
    /**
     * 已还提前还款手续费
     */
    @Column(name = "pre_pmt_fee_repay")
    private String prePmtFeeRepay;
    /**
     * 贷款状态
     */
    @Column(name = "loan_status")
    private String loanStatus;
    /**
     * 贷款形态
     */
    @Column(name = "loan_form")
    private String loanForm;
    /**
     * 逾期天数
     */
    @Column(name = "days_ovd")
    private String daysOvd;
    /**
     * 附加事件
     */
    @Column(name = "add_event")
    private String addEvent;
    /**
     * 非应计状态
     */
    @Column(name = "interest_transfer_status")
    private String interestTransferStatus;
}
