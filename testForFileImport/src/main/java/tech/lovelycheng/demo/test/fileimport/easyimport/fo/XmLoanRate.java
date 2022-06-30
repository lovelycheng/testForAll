package tech.lovelycheng.demo.test.fileimport.easyimport.fo;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author chengtong
 * @date 2022/6/28 15:08
 */
@Data
@Table("loan_rate")
public class XmLoanRate extends Archived {
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
     * 开始期序
     */
    @Column(name = "start_term")
    private String startTerm;
    /**
     * 结束期序
     */
    @Column(name = "end_term")
    private String endTerm;
    /**
     * 正常利率
     */
    @Column(name = "rate")
    private String rate;
    /**
     * 正常利率类型
     */
    @Column(name = "rate_unit")
    private String rateUnit;
    /**
     * 罚息利率
     */
    @Column(name = "ovd_rate")
    private String ovdRate;
    /**
     * 罚息利率类型
     */
    @Column(name = "ovd_rate_unit")
    private String ovdRateUnit;
    /**
     * 复息利率
     */
    @Column(name = "cmpd_rate")
    private String cmpdRate;
    /**
     * 复息利率类型
     */
    @Column(name = "cmpd_rate_unit")
    private String cmpdRateUnit;
    /**
     * 提前还款手续费率
     */
    @Column(name = "pre_pmt_fee_rate")
    private String prePmtFeeRate;

}
