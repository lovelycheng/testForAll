package tech.lovelycheng.demo.test.fileimport.easyimport.fo;

import lombok.Data;

/**
 * @author chengtong
 * @date 2022/6/28 16:02
 */
@Data
public class Archived {
    /**
     * 账务日期
     */
    @Column(name = "cur_date")
    private String curDate;
}
