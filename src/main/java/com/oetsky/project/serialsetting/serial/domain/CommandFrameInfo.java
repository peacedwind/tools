package com.oetsky.project.serialsetting.serial.domain;

import java.util.Date;

/**
 * 通讯命令帧记录表 command_frame_info
 *
 * @author oetsky
 * @date 2021-05-31
 */
public class CommandFrameInfo {

	private Long id;
	/**
	 * 设备类型(1威胜电能表,2能源控制器)
	 */
	private Integer communicationType;
	/**
	 * 发送类型(1接收,2发送)
	 */
	private Integer sendType;
	/**
	 * 通道号
	 */
	private Integer channelNum;
	/**
	 * 相序
	 */
	private String phaseSeq;
	/**
	 * 命令帧时间
	 */
	private Date sendTime;
	/**
	 * 表计地址
	 */
	private String meterAddr;
	/**
	 * 命令帧数据OAD
	 */
	private String frameOad;
	/**
	 * 命令帧数据OAD名称
	 */
	private String frameOadName;
	/**
	 * 命令帧
	 */
	private String frameData;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 是否正常(1正常,2异常)
	 */
	private Integer isNormal;
	/**
	 * 描述
	 */
	private String remark;

	/**
	 * 获取
	 *
	 * @return id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * 设置
	 *
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取 设备类型(1威胜电能表2能源控制器)
	 *
	 * @return communicationType 设备类型(1威胜电能表2能源控制器)
	 */
	public Integer getCommunicationType() {
		return this.communicationType;
	}

	/**
	 * 设置 设备类型(1威胜电能表2能源控制器)
	 *
	 * @param communicationType 设备类型(1威胜电能表2能源控制器)
	 */
	public void setCommunicationType(Integer communicationType) {
		this.communicationType = communicationType;
	}

	/**
	 * 获取 发送类型(1接收2发送)
	 *
	 * @return sendType 发送类型(1接收2发送)
	 */
	public Integer getSendType() {
		return this.sendType;
	}

	/**
	 * 设置 发送类型(1接收2发送)
	 *
	 * @param sendType 发送类型(1接收2发送)
	 */
	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}

	/**
	 * 获取 通道号
	 *
	 * @return channelNum 通道号
	 */
	public Integer getChannelNum() {
		return this.channelNum;
	}

	/**
	 * 设置 通道号
	 *
	 * @param channelNum 通道号
	 */
	public void setChannelNum(Integer channelNum) {
		this.channelNum = channelNum;
	}

	/**
	 * 获取 相序
	 *
	 * @return phaseSeq 相序
	 */
	public String getPhaseSeq() {
		return this.phaseSeq;
	}

	/**
	 * 设置 相序
	 *
	 * @param phaseSeq 相序
	 */
	public void setPhaseSeq(String phaseSeq) {
		this.phaseSeq = phaseSeq;
	}

	/**
	 * 获取 命令帧时间
	 *
	 * @return sendTime 命令帧时间
	 */
	public Date getSendTime() {
		return this.sendTime;
	}

	/**
	 * 设置 命令帧时间
	 *
	 * @param sendTime 命令帧时间
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * 获取 表计地址
	 *
	 * @return meterAddr 表计地址
	 */
	public String getMeterAddr() {
		return this.meterAddr;
	}

	/**
	 * 设置 表计地址
	 *
	 * @param meterAddr 表计地址
	 */
	public void setMeterAddr(String meterAddr) {
		this.meterAddr = meterAddr;
	}

	/**
	 * 获取 命令帧数据OAD
	 *
	 * @return frameOad 命令帧数据OAD
	 */
	public String getFrameOad() {
		return this.frameOad;
	}

	/**
	 * 设置 命令帧数据OAD
	 *
	 * @param frameOad 命令帧数据OAD
	 */
	public void setFrameOad(String frameOad) {
		this.frameOad = frameOad;
	}

	/**
	 * 获取 命令帧数据OAD名称
	 *
	 * @return frameOadName 命令帧数据OAD名称
	 */
	public String getFrameOadName() {
		return this.frameOadName;
	}

	/**
	 * 设置 命令帧数据OAD名称
	 *
	 * @param frameOadName 命令帧数据OAD名称
	 */
	public void setFrameOadName(String frameOadName) {
		this.frameOadName = frameOadName;
	}

	/**
	 * 获取 命令帧
	 *
	 * @return frameData 命令帧
	 */
	public String getFrameData() {
		return this.frameData;
	}

	/**
	 * 设置 命令帧
	 *
	 * @param frameData 命令帧
	 */
	public void setFrameData(String frameData) {
		this.frameData = frameData;
	}

	/**
	 * 获取 创建时间
	 *
	 * @return createTime 创建时间
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 设置 创建时间
	 *
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取 是否正常(1正常2异常)
	 *
	 * @return isNormal 是否正常(1正常2异常)
	 */
	public Integer getIsNormal() {
		return this.isNormal;
	}

	/**
	 * 设置 是否正常(1正常2异常)
	 *
	 * @param isNormal 是否正常(1正常2异常)
	 */
	public void setIsNormal(Integer isNormal) {
		this.isNormal = isNormal;
	}

	/**
	 * 获取 描述
	 *
	 * @return remark 描述
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 设置 描述
	 *
	 * @param remark 描述
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
}