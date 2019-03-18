/*
 * FileName: ViedoDetialController.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.ultrapower.viedo.bean.BrowseRecord;
import com.ultrapower.viedo.bean.PraiseRecord;
import com.ultrapower.viedo.bean.SearchContent;
import com.ultrapower.viedo.bean.ViedoDetial;
import com.ultrapower.viedo.constants.Constants;
import com.ultrapower.viedo.constants.ViedoSystemResult;
import com.ultrapower.viedo.service.BrowseRecordService;
import com.ultrapower.viedo.service.PariseRecordService;
import com.ultrapower.viedo.service.ViedoDetialService;
import com.ultrapower.viedo.vo.RepData;
import com.ultrapower.viedo.vo.ViedoDetialVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author dell
 * @version 4.1

 * <p>
 * History:
 *
 * Date                     Author         Version     Description
 * ---------------------------------------------------------------------------------
 * 2017年11月29日 下午5:09:41          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
@Controller
@RequestMapping("/ViedoDetial/")
@Api(tags = { "Viedo" }, value = "关于视频详情接口")
public class ViedoDetialController {
    @Autowired
    private ViedoDetialService viedoDetialService;

    @Autowired
    private BrowseRecordService browseRecordService;

    @Autowired
    private PariseRecordService pariseRecordService;

    /**
     * 获取视频详情
     * @return
     */
    @RequestMapping("index.do")
    @ApiOperation(httpMethod = "GET", value = "Find purchase order by ID", consumes = "application/x-www-form-urlencoded", notes = "视频上传页面")
    public String index() {
        return "page/upload";
    }

    /**
     * 获取视频详情
     * @return
     */
    @RequestMapping("indexH5.do")
    @ApiOperation(httpMethod = "GET", value = "Find purchase order by ID", consumes = "application/x-www-form-urlencoded", notes = "H5上传页面")
    public String indexH5() {
        return "page/uploadH5";
    }

    /**
     * 获取视频详情
     * @return
     */
    @RequestMapping("update.do")
    @ApiOperation(httpMethod = "GET", value = "Find purchase order by ID", consumes = "application/x-www-form-urlencoded", notes = "视频修改页面")
    public String update() {
        return "page/update";
    }

    /**
     * 获取视频详情
     * @return
     */
    @RequestMapping("updateH5.do")
    @ApiOperation(httpMethod = "GET", value = "Find purchase order by ID", consumes = "application/x-www-form-urlencoded", notes = "H5修改页面")
    public String updateH5() {
        return "page/updateH5";
    }

    /**
     * 获取视频详情通过ID
     * @return
     */
    @RequestMapping("getViedoDetial.do")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "Find purchase order by ID", consumes = "application/x-www-form-urlencoded", notes = "获取视频详情通过ID")
    public RepData<ViedoDetialVo> getViedoDetial(@RequestBody @ApiParam(name = "视频对象", value = "凭借字符串") ViedoDetial detial) {
        Map<String, Object> map = new HashMap<String, Object>();
        RepData<ViedoDetialVo> repData = new RepData<ViedoDetialVo>();
        repData.setCode(ViedoSystemResult.Success.getCode());
        repData.setMsg(ViedoSystemResult.Success.getMsg());
        repData.setCount(0);
        repData.setData(null);

        if (detial.getId() != 0) {
            //通过ID 获取视频详情 用于视频播放
            map.put("id", detial.getId());
            map.put("pageSize", "1");
            map.put("pageNum", "0");

            ViedoDetialVo vo = viedoDetialService.getViedoDetailById(map);
            //Integer count = viedoDetialService.getCountViedoDetail();
            if (vo != null) {
                repData.setCount(1);
                //(0视频课程，1H5课程)
                //Constants.VK_DOWN_URL + strings[1];
                if (!vo.getImgUrl().startsWith(Constants.VK_DOWN_URL)) {
                    vo.setImgUrl(Constants.VK_DOWN_URL + vo.getImgUrl());
                    vo.setSmallImgUrl(Constants.VK_DOWN_URL + vo.getSmallImgUrl());
                    vo.setMaxImgUrl(Constants.VK_DOWN_URL + vo.getMaxImgUrl());
                    if (vo.getViedoType() == 1) {
                        vo.setCodeUrl(Constants.VK_DOWN_URL + vo.getCodeUrl());

                    } else {
                        vo.setViedoUrl(Constants.VK_DOWN_URL + vo.getViedoUrl());
                    }
                }

                repData.setData(vo);
            }

        }

        return repData;

    }

    /**
     * 获取视频详情列表
     * @return
     */
    @RequestMapping("getViedoDetialList.do")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "Find purchase order by ID", consumes = "application/x-www-form-urlencoded", notes = "获取视频详情列表")
    public RepData<List<ViedoDetialVo>> getViedoDetialList(@ApiParam(name = "视频对象", value = "凭借字符串") ViedoDetial detial, @ApiParam(name = "分页大小", value = "大小", required = true) int pageSize, @ApiParam(name = "分页页码", value = "页码", required = true) int pageNum) {
        Map<String, Object> map = new HashMap<String, Object>();
        RepData<List<ViedoDetialVo>> repData = new RepData<List<ViedoDetialVo>>();
        repData.setCode(ViedoSystemResult.Success.getCode());
        repData.setMsg(ViedoSystemResult.Success.getMsg());
        repData.setCount(0);
        repData.setData(null);

        //获取所有的视频详情 并通过 上传时间排序   用于首页列表
        if (pageNum >= 0 && pageSize > 0) {
            if (detial.getViedoClassifyId() != 0) {
                map.put("viedoClassifyId", detial.getViedoClassifyId());
            }
            if (StringUtils.isNotBlank(detial.getUserName())) {
                map.put("userName", detial.getUserName());
            }
            map.put("orderByUploadTime", "orderByUploadTime");
            map.put("pageSize", pageSize);
            map.put("pageNum", (pageNum - 1) * pageSize);

            int page = 1; //页号
            pageSize = 20; //每页数据条数
            String sortString = "createTime.desc";//如果你想排序的话逗号分隔可以排序多列

            PageBounds pageBounds = new PageBounds(page, pageSize, Order.formString(sortString), true);
            //PageBounds pageBounds = new PageBounds((pageNum - 1) * pageSize, pageSize, Order.formString("createTime.desc"), true);
            PageList<ViedoDetialVo> list = (PageList<ViedoDetialVo>) viedoDetialService.getViedoDetail(map, pageBounds);
            //Integer count = viedoDetialService.getCountViedoDetail(map);
            if (list != null) {
                /* for (ViedoDetialVo vo : list) {
                    if (!vo.getImgUrl().startsWith(Constants.VK_DOWN_URL)) {
                        vo.setImgUrl(Constants.VK_DOWN_URL + vo.getImgUrl());
                        vo.setSmallImgUrl(Constants.VK_DOWN_URL + vo.getSmallImgUrl());
                        vo.setMaxImgUrl(Constants.VK_DOWN_URL + vo.getMaxImgUrl());
                        if (vo.getViedoType() == 1) {
                            vo.setCodeUrl(Constants.VK_DOWN_URL + vo.getCodeUrl());
                
                        } else {
                            vo.setViedoUrl(Constants.VK_DOWN_URL + vo.getViedoUrl());
                        }
                    }
                }*/
                repData.setCount(list.getPaginator().getTotalCount());
                repData.setData(list);
            }

        }
        return repData;
    }

    /**
     * 上传临时视频
     * @return
     */
    @RequestMapping("uploadTempViedo.do")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "Find purchase order by ID", consumes = "application/x-www-form-urlencoded", notes = " 上传临时视频")
    public RepData<String> uploadTempViedo(@RequestParam("file") @ApiParam(name = "视频文件", value = "传入文件流", required = true) CommonsMultipartFile viedo) {
        RepData<String> repData = new RepData<String>();
        repData.setCode(ViedoSystemResult.Empty.getCode());
        repData.setMsg(ViedoSystemResult.Empty.getMsg());
        repData.setData(null);
        repData.setCount(0);
        if (!viedo.isEmpty()) {
            return viedoDetialService.uploadTempViedo(viedo);
        }
        return repData;

    }

    /**
     * 上传临时图片
     * @param img 图片
     * @param type 标记是否是二维码 1是 0不是
     * @return
     */
    @RequestMapping("uploadTempImg.do")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "Find purchase order by ID", consumes = "application/x-www-form-urlencoded", notes = " 上传临时图片")
    public RepData<String> uploadTempImg(@RequestParam("file") @ApiParam(name = "图片文件", value = "传入文件流", required = true) CommonsMultipartFile img) {
        RepData<String> repData = new RepData<String>();
        repData.setCode(ViedoSystemResult.Empty.getCode());
        repData.setMsg(ViedoSystemResult.Empty.getMsg());
        repData.setData(null);
        repData.setCount(0);
        if (!img.isEmpty()) {
            return viedoDetialService.uploadTempImg(img);
        }
        return repData;

    }

    /**
     * 上传临时二维码
     * @param code 二维码
     * @return
     */
    @RequestMapping("uploadTempCode.do")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "Find purchase order by ID", consumes = "application/x-www-form-urlencoded", notes = "上传临时二维码")
    public RepData<String> uploadTempCode(@RequestParam("file") @ApiParam(name = "图片文件", value = "传入文件流", required = true) CommonsMultipartFile code) {
        RepData<String> repData = new RepData<String>();
        repData.setCode(ViedoSystemResult.Empty.getCode());
        repData.setMsg(ViedoSystemResult.Empty.getMsg());
        repData.setData(null);
        repData.setCount(0);
        if (!code.isEmpty()) {
            return viedoDetialService.uploadTempCode(code);
        }
        return repData;

    }

    /**
     * 上传视频
     * @return
     */
    @RequestMapping("uploadViedo.do")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "Find purchase order by ID", consumes = "application/x-www-form-urlencoded", notes = "上传视频")
    public RepData<Integer> uploadViedo(@ApiParam(name = "图片临时路径", value = "图片临时路径字符串", required = true) String imgURL, @ApiParam(name = "视频基本信息", value = "视频临时路径字符串", required = true) String viedoURL, @ApiParam(name = "视频临时路径", value = "频临时路径实体类", required = true) ViedoDetial vo, @ApiParam(name = "视频名称", value = "视频名称的字符串", required = true) String viedoName, @ApiParam(name = "图片的名称", value = "图片的名称字符串", required = true) String imgName) {
        RepData<Integer> repData = new RepData<Integer>();
        repData.setCode(ViedoSystemResult.Success.getCode());
        repData.setMsg(ViedoSystemResult.Success.getMsg());
        repData.setData(0);
        repData.setCount(0);
        //判断路径是否为空
        if (StringUtils.isNotBlank(imgURL)) {
            if (StringUtils.isNotBlank(viedoURL)) {
                //判断必须参数是否为空  用户名  视频分类ID 视频名称 视频描述
                if (StringUtils.isNotBlank(imgName) && StringUtils.isNotBlank(viedoName) && StringUtils.isNotBlank(vo.getUserName()) && vo.getViedoClassifyId() != 0 && StringUtils.isNotBlank(vo.getViedoTitle()) && StringUtils.isNotBlank(vo.getViedoDescription())) {
                    return viedoDetialService.uploadViedo(imgURL, viedoURL, vo, viedoName, imgName);
                } else {
                    repData.setCode(ViedoSystemResult.Empty.getCode());
                    repData.setMsg(ViedoSystemResult.Empty.getMsg());
                }
            } else {
                repData.setCode(ViedoSystemResult.Empty.getCode());
                repData.setMsg("viedoURL" + ViedoSystemResult.Empty.getMsg());
            }

        } else {
            repData.setCode(ViedoSystemResult.Empty.getCode());
            repData.setMsg("imgURL" + ViedoSystemResult.Empty.getMsg());
        }
        return repData;

    }

    /**
     * 上传H5
     * @return
     */
    @RequestMapping("uploadH5.do")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "Find purchase order by ID", consumes = "application/x-www-form-urlencoded", notes = "上传H5")
    @ApiImplicitParams({ @ApiImplicitParam(name = "图片临时路径", value = "图片临时路径字符串", required = true), @ApiImplicitParam(name = "二维码基本信息", value = "二维码临时路径字符串", required = true), @ApiImplicitParam(name = "H5基本信息", value = "H5临时路径字符串", required = true), @ApiImplicitParam(name = "视频临时路径", value = "频临时路径实体类", required = true), @ApiImplicitParam(name = "H5名称", value = "H5名称的字符串", required = true), @ApiImplicitParam(name = "图片的名称", value = "图片的名称字符串", required = true) })
    public RepData<Integer> uploadH5(String imgURL, String codeURL, String H5URL, ViedoDetial vo, String codeName, String imgName) {
        RepData<Integer> repData = new RepData<Integer>();
        repData.setCode(ViedoSystemResult.Empty.getCode());
        repData.setMsg(ViedoSystemResult.Empty.getMsg());
        repData.setData(0);
        repData.setCount(0);
        //判断路径是否为空
        if (StringUtils.isNotBlank(codeName) && StringUtils.isNotBlank(imgName) && StringUtils.isNotBlank(imgURL) && StringUtils.isNotBlank(codeURL) && StringUtils.isNotBlank(H5URL)) {
            //判断必须参数是否为空  用户名  视频分类ID 视频名称 视频描述
            if (StringUtils.isNotBlank(vo.getViedoTitle()) && vo.getViedoClassifyId() != 0 && StringUtils.isNotBlank(vo.getUserName()) && StringUtils.isNotBlank(vo.getViedoDescription())) {
                return viedoDetialService.uploadH5(imgURL, codeURL, H5URL, vo, codeName, imgName);
            }
        }
        return repData;

    }

    /**
     * 点赞
     * @return
     */
    @RequestMapping("addpraiseRecord.do")
    @ApiOperation(httpMethod = "GET", value = "Find purchase order by ID", consumes = "application/x-www-form-urlencoded", notes = "点赞")
    @ResponseBody
    public RepData<Integer> addPraiseRecord(@ApiParam(name = "点赞记录", value = "点赞记录实体类", required = true) PraiseRecord record) {
        RepData<Integer> repData = new RepData<Integer>();
        repData.setCode(ViedoSystemResult.Success.getCode());
        repData.setMsg(ViedoSystemResult.Success.getMsg());
        repData.setData(0);
        repData.setCount(0);
        int result = pariseRecordService.addPariseRecord(record);

        if (result > 0) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", record.getViedoId());
            ViedoDetialVo vo = viedoDetialService.getViedoDetailById(map);
            viedoDetialService.addViedoDetialToSolr(vo);
            repData.setData(result);
            repData.setCount(1);
        }
        return repData;

    }

    /**
     * 点赞
     * @return
     */
    @RequestMapping("validUserPraiseRecord.do")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "Find purchase order by ID", consumes = "application/x-www-form-urlencoded", notes = "校验是否点过点赞")
    public RepData<Boolean> validUserPraiseRecord(@ApiParam(name = "点赞记录", value = "点赞记录实体类", required = true) PraiseRecord record) {

        return pariseRecordService.getPariseRecord(record);

    }

    /**
     * 播放记录
     * @return
     */
    @RequestMapping("addBrowseRecord.do")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "Find purchase order by ID", consumes = "application/x-www-form-urlencoded", notes = "播放记录")
    public RepData<Integer> addBrowseRecord(@ApiParam(name = "点赞记录", value = "点赞记录实体类", required = true) BrowseRecord record) {
        RepData<Integer> repData = new RepData<Integer>();
        repData.setCode(ViedoSystemResult.Success.getCode());
        repData.setMsg(ViedoSystemResult.Success.getMsg());
        repData.setData(0);
        repData.setCount(0);
        int result = browseRecordService.addBrowseRecord(record);
        if (result > 0) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", record.getViedoId());
            ViedoDetialVo vo = viedoDetialService.getViedoDetailById(map);
            viedoDetialService.addViedoDetialToSolr(vo);
            repData.setData(result);
            repData.setCount(1);
        }
        return repData;

    }

    /**
     * 更新普通视频
     * @return
     */
    @RequestMapping("updateViedoDetial.do")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "Find purchase order by ID", consumes = "application/x-www-form-urlencoded", notes = "更新普通视频")
    public RepData<Integer> updateViedoDetial(@ApiParam(name = "图片临时路径", value = "图片临时路径字符串", required = true) String imgURL, @ApiParam(name = "视频基本信息", value = "视频临时路径字符串", required = true) String viedoURL, @ApiParam(name = "视频临时路径", value = "频临时路径实体类", required = true) ViedoDetial vo, @ApiParam(name = "视频名称", value = "视频名称的字符串", required = true) String viedoName, @ApiParam(name = "图片的名称", value = "图片的名称字符串", required = true) String imgName) {

        RepData<Integer> repData = new RepData<Integer>();
        if (vo.getId() == 0) {
            repData.setCode(ViedoSystemResult.Empty.getCode());
            repData.setMsg(ViedoSystemResult.Empty.getMsg());
            repData.setData(null);
            repData.setCount(0);
            return repData;
        }
        return viedoDetialService.updateViedoDetail(imgURL, viedoURL, vo, viedoName, imgName);

    }

    /**
     * 更新H5视频
     * @return
     */
    @RequestMapping("updateH5ViedoDetial.do")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "Find purchase order by ID", consumes = "application/x-www-form-urlencoded", notes = "更新H5视频")
    @ApiImplicitParams({ @ApiImplicitParam(name = "图片临时路径", value = "图片临时路径字符串", required = true), @ApiImplicitParam(name = "二维码基本信息", value = "二维码临时路径字符串", required = true), @ApiImplicitParam(name = "H5基本信息", value = "H5临时路径字符串", required = true), @ApiImplicitParam(name = "视频临时路径", value = "频临时路径实体类", required = true), @ApiImplicitParam(name = "H5名称", value = "H5名称的字符串", required = true), @ApiImplicitParam(name = "图片的名称", value = "图片的名称字符串", required = true) })

    public RepData<Integer> updateH5ViedoDetial(String imgURL, String codeURL, String H5URL, ViedoDetial detial, String codeName, String imgName) {

        return viedoDetialService.updateH5ViedoDetail(imgURL, codeURL, H5URL, detial, codeName, imgName);

    }

    /**
     * 搜索
     * @param type 课程类型
     * @param classIfy 课程分类
     * @param userName 用户名
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param context 关键字
     * @return
     */
    @RequestMapping("searchViedoDetial.do")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "Find purchase order by ID", consumes = "application/x-www-form-urlencoded", notes = "搜索")
    public RepData<List<Map<String, Object>>> searchViedoDetial(@ApiParam(name = "图片临时路径", value = "图片临时路径字符串", required = true) SearchContent sContent) {
        RepData<List<Map<String, Object>>> repData = new RepData<List<Map<String, Object>>>();
        repData.setCode(ViedoSystemResult.Empty.getCode());
        repData.setMsg(ViedoSystemResult.Empty.getMsg());
        repData.setData(null);
        repData.setCount(0);
        if (sContent.getPageNum() > 0 && sContent.getPageSize() > 0) {

            return viedoDetialService.searchViedoDetial(sContent);

        }
        return repData;

    }

    /**
     * 删除
     * @param context 关键字
     * @return
     */
    @ApiOperation(httpMethod = "GET", value = "Find purchase order by ID", consumes = "application/x-www-form-urlencoded", notes = "删除")
    @RequestMapping("deleteViedoDetial.do")
    @ResponseBody
    public RepData<Integer> deleteViedoDetial(@ApiParam(name = "视频信息", value = "视频信息实体类", required = true) ViedoDetial viedoDetial) {
        return viedoDetialService.deleteViedoDetial(viedoDetial);

    }

    @RequestMapping("addSolrViedoDetail.do")
    @ResponseBody
    @ApiOperation(httpMethod = "GET", value = "Find purchase order by ID", consumes = "application/x-www-form-urlencoded", notes = "添加solr")
    @ApiImplicitParam(name = "视频ID", value = "视频信息ID", required = true)
    public RepData<ViedoDetialVo> addSolrViedoDetail(int id) {
        Map<String, Object> map = new HashMap<String, Object>();
        RepData<ViedoDetialVo> repData = new RepData<ViedoDetialVo>();
        repData.setCode(ViedoSystemResult.Success.getCode());
        repData.setMsg(ViedoSystemResult.Success.getMsg());
        repData.setCount(0);
        repData.setData(null);

        if (id != 0) {
            //通过ID 获取视频详情 用于视频播放
            map.put("id", id);
            map.put("pageSize", "1");
            map.put("pageNum", "0");
            ViedoDetialVo vo = viedoDetialService.getViedoDetailById(map);
            //Integer count = viedoDetialService.getCountViedoDetail();
            if (vo != null) {
                repData.setCount(1);
                //(0视频课程，1H5课程)
                //Constants.VK_DOWN_URL + strings[1];
                viedoDetialService.addViedoDetialToSolr(vo);
                repData.setData(vo);
            }

        }
        return repData;

    }

}
