package tech.lovelycheng.demo.test.fileimport.easyimport.fo;

import lombok.Data;

/**
 * @author chengtong
 * @date 2022/6/28 15:07
 */
@Data
@Table("repay")
public class XmRepay extends Archived{

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
     * 交易日期
     */
    @Column(name = "tran_date")
    private String tranDate;
    /**
     * 交易时间
     */
    @Column(name = "tran_time")
    private String tranTime;
    /**
     * 交易流水号
     */
    @Column(name = "seq_no")
    private String seqNo;
    /**
     * 交易金额
     */
    @Column(name = "total_amt")
    private String totalAmt;
    /**
     * 实收金额
     */
    @Column(name = "income_amt")
    private String incomeAmt;
    /**
     * 本金发生额
     */
    @Column(name = "prin_amt")
    private String prinAmt;
    /**
     * 利息发生额
     */
    @Column(name = "int_amt")
    private String intAmt;
    /**
     * 罚息发生额
     */
    @Column(name = "pnlt_int_amt")
    private String pnltIntAmt;
    /**
     * 复息发生额
     */
    @Column(name = "int_pnlt_amt")
    private String intPnltAmt;
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
