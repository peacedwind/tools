package com.oetsky.project.constants;

/**
 * 采集数据常量
 * @author: huwm
 * @date: 2019/5/15
 */
public class ReceiveDataConstants {


    public enum Quality{
        //品质正常
        NORMAL(10000L),
        //品质异常
        ERROR(-99999L),
        ;

        Quality(long code){
            this.code = code ;
        }

        private long code ;

        public long getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    private ReceiveDataConstants(){}

    /**
     * 采集数据时间 60秒一条 ,当 秒数 为0的时候为采集数据
     */
    public static final int SIMPLE_DATA_TIME = 0 ;

    /**
     * 帧头长度
     */
    public static final int FRAME_HEADER_LENGTH = 8 ;

    /**
     * 测量时间长度
     */
    public static final int MEASURING_TIME_LENGTH = 14 ;

    /**
     * 接收标志长度
     */
    public static final int RECEIVE_FLAG_LENGTH = 2 ;

    /**
     * 12路数据长度
     *
     */
    public static final int ALL_CHANNEL_LENGTH = 2256 ;

    /**
     * 帧尾长度
     */
    public static final int FRAME_FOOTER_LENGTH = 8 ;

    /**
     * 串口接收到的数据字节
     */
    public static final int SERIAL_DATA_BYTE_LENGTH = 1144 ;

    /**
     * 串口接收到的数据总长度
     */
    public static final int DATA_SUM_LENGTH = 2288 ;

    /**
     * 串口接收数据帧头
     */
    public static final String RECEIVE_DATA_FRAME_HEADER = "AA55AA55";

    /**
     * 串口接收数据帧尾
     */
    public static final String RECEIVE_DATA_FRAME_FOOTER = "AAFFAAFF";

    /**
     * 一个通道的数据长度
     */
    public static final int ONE_CHANNEL_LENGTH = 184 ;
    /**
     * 二个通道的数据长度
     */
    public static final int TWO_CHANNEL_LENGTH = ONE_CHANNEL_LENGTH * 2 ;

    /**
     * 两个通道数据后的帧尾长度
     */
    public static final int TWO_CHANNEL_FRAME_FOOTER_LENGTH = 8 ;

    /**
     * 两路数据长度 + 两路后的帧尾长度 和
     */
    public static final int TWO_CHANNEL_AND_FRAME_FOOTER_LENGTH = (TWO_CHANNEL_LENGTH + TWO_CHANNEL_FRAME_FOOTER_LENGTH) ;

    /**
     * 板的数量
     */
    public static final int BOARD_COUNT = 6 ;

    public static final int ONE_PHASE_LENGTH = 56;

    /**
     * 三相长度
     */
    public static final int THREE_PHASE_LENGTH = ONE_PHASE_LENGTH * 3 ;

    /**
     * 公共长度 8
     */
    public static final int COMMON_LENGTH = 8 ;


    /**
     * 基波频率-保留小数位
     */
    public static final int FUNDAMENTAL_FREQUENCY_SCALE = 5;

    /**
     * 基波有效值-保留小数位
     */
    public static final int FUNDAMENTAL_AMPLITUDE_SCALE = 5;

    /**
     * 谐波有效值-保留小数位
     */
    public static final int HARMONIC_AMPLITUDE_SCALE = 7;

    /**
     * 基波相位-保留小数位
     */
    public static final int FUNDAMENTAL_PHASE_SCALE = 2;

    /**
     * 谐波相位-保留小数位
     */
    public static final int HARMONIC_PHASEP_SCALE = 2;

    /**
     * 电压不平衡度-保留小数位
     */
    public static final int VOLTAGE_IMBALANCE_SCALE = 2;

    /**
     *
     * 误差均值_保留小数位
     */
    public static final int AVERAGE_SCALE = 15;

    /**
     *
     * 误差方差-保留小数位
     */
    public static final int VARIANCE_SCALE = 15;

    /**
     * 采集数据RECEIVE
     */
    public static final String DATA_TYPE_RECEIVE = "RECEIVE";

    /**
     * 抽样数据SAMPLE
     */
    public static final String DATA_TYPE_SAMPLE = "SAMPLE";

}
