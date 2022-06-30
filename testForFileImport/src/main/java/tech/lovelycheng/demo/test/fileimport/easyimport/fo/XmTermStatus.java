package tech.lovelycheng.demo.test.fileimport.easyimport.fo;

import lombok.Data;

/**
 * @author chengtong
 * @date 2022/6/28 15:06
 */
@Data
@Table("term_status")
public class XmTermStatus extends Archived{

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
     * 期序
     */
    @Column(name = "term_no")
    private String termNo;
    /**
     * 开始日期
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
     * 减免利息发生额
     */
    @Column(name = "int_reduce_amt")
    private String intReduceAmt;
    /**
     * 减免罚息发生额
     */
    @Column(name = "pnlt_reduce_amt")
    private String pnltReduceAmt;
    /**
     * 减免复息发生额
     */
    @Column(name = "int_pnlt_reduce_amt")
    private String intPnltReduceAmt;
    /**
     * 本期状态
     */
    @Column(name = "term_status")
    private String termStatus;
    /**
     * 逾期天数
     */
    @Column(name = "days_ovd")
    private String daysOvd;
    /**
     * 已还提前还款手续费
     */
    @Column(name = "pre_pmt_fee_repay")
    private String prePmtFeeRepay;
    /**
     * 当天借当天还利息
     */
    @Column(name = "int_today")
    private String intToday;
}
