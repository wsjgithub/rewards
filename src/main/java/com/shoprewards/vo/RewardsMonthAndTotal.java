package com.shoprewards.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RewardsMonthAndTotal {
    private List<Long> rewardsPerMonth;
    private List<String> months;
    private Long total;
}
