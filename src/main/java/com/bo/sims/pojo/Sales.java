package com.bo.sims.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bo
 * @create 2021-06-18 10:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sales {

    private Long gid;
    private Long bid;
    private Long salesCount;

    private Goods goods;
    private Bill bill;

    public Sales(Long gid, Long bid, Long salesCount) {
        this.gid = gid;
        this.bid = bid;
        this.salesCount = salesCount;
    }

    public Sales(Long gid, Long salesCount) {
        this.gid = gid;
        this.salesCount = salesCount;
    }
}
