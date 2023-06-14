package com.oetsky.project.enums;


import com.oetsky.project.constants.ReceiveDataConstants;

/**
 * 数据下标
 * @author: huwm
 * @date: 2019/5/15
 */
public enum ReceiveDataIndexEnum {

    /**
     * 帧头下标 (0,8)
     */
    FRAME_HEADER_INDEX(0, ReceiveDataConstants.FRAME_HEADER_LENGTH),

    /**
     * 测量时间下标(8,8+14)
     */
    MEASURING_TIME_INDEX(FRAME_HEADER_INDEX.getEndIndex(),FRAME_HEADER_INDEX.getEndIndex() + ReceiveDataConstants.MEASURING_TIME_LENGTH),

    /**
     * 接收标志下标(8+14,8+14+2)
     */
    RECEIVE_FLAG_INDEX(MEASURING_TIME_INDEX.getEndIndex(),MEASURING_TIME_INDEX.getEndIndex() + ReceiveDataConstants.RECEIVE_FLAG_LENGTH),

    /**
     * 12路数据下标(8+14+2,8+14+2+2256)
     */
    ALL_CHANNEL_INDEX(RECEIVE_FLAG_INDEX.getEndIndex(),RECEIVE_FLAG_INDEX.getEndIndex()+ ReceiveDataConstants.ALL_CHANNEL_LENGTH),

    /**
     * 帧尾下标(8+14+2+2256,8+14+2+2256+8)
     */
    FRAME_FOOTER_INDEX(ALL_CHANNEL_INDEX.getEndIndex(),ALL_CHANNEL_INDEX.getEndIndex()+ ReceiveDataConstants.FRAME_FOOTER_LENGTH)

//    /**
//     * 第2通道后的帧尾下标
//     */
//    TWO_CHANNEL_FRAME_FOOTER_INDEX(RECEIVE_FLAG_INDEX.getEndIndex()+ReceiveDataConstants.TWO_CHANNEL_LENGTH,
//            RECEIVE_FLAG_INDEX.getEndIndex()+ReceiveDataConstants.TWO_CHANNEL_AND_FRAME_FOOTER_LENGTH),
//
//    /**
//     * 第4通道后的帧尾下标
//     */
//    FOUR_CHANNEL_FRAME_FOOTER_INDEX(TWO_CHANNEL_FRAME_FOOTER_INDEX.getEndIndex()+ReceiveDataConstants.TWO_CHANNEL_LENGTH,
//            TWO_CHANNEL_FRAME_FOOTER_INDEX.getEndIndex()+ReceiveDataConstants.TWO_CHANNEL_AND_FRAME_FOOTER_LENGTH),
//
//    /**
//     * 第6通道后的帧尾下标
//     */
//    SIX_CHANNEL_FRAME_FOOTER_INDEX(FOUR_CHANNEL_FRAME_FOOTER_INDEX.getEndIndex()+ReceiveDataConstants.TWO_CHANNEL_LENGTH,
//            FOUR_CHANNEL_FRAME_FOOTER_INDEX.getEndIndex()+ReceiveDataConstants.TWO_CHANNEL_AND_FRAME_FOOTER_LENGTH),
//
//    /**
//     * 第8通道后的帧尾下标
//     */
//    EIGHT_CHANNEL_FRAME_FOOTER_INDEX(SIX_CHANNEL_FRAME_FOOTER_INDEX.getEndIndex()+ReceiveDataConstants.TWO_CHANNEL_LENGTH,
//            SIX_CHANNEL_FRAME_FOOTER_INDEX.getEndIndex()+ReceiveDataConstants.TWO_CHANNEL_AND_FRAME_FOOTER_LENGTH),
//
//    /**
//     * 第10通道后的帧尾下标
//     */
//    TEN_CHANNEL_FRAME_FOOTER_INDEX(EIGHT_CHANNEL_FRAME_FOOTER_INDEX.getEndIndex()+ReceiveDataConstants.TWO_CHANNEL_LENGTH,
//            EIGHT_CHANNEL_FRAME_FOOTER_INDEX.getEndIndex()+ReceiveDataConstants.TWO_CHANNEL_AND_FRAME_FOOTER_LENGTH),
//
//    /**
//     * 第12通道后的帧尾下标
//     */
//    TWELVE_CHANNEL_FRAME_FOOTER_INDEX(TEN_CHANNEL_FRAME_FOOTER_INDEX.getEndIndex()+ReceiveDataConstants.TWO_CHANNEL_LENGTH,
//            TEN_CHANNEL_FRAME_FOOTER_INDEX.getEndIndex()+ReceiveDataConstants.TWO_CHANNEL_AND_FRAME_FOOTER_LENGTH)
    ;

    ReceiveDataIndexEnum(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    private int startIndex ;

    private int endIndex ;


    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
}
