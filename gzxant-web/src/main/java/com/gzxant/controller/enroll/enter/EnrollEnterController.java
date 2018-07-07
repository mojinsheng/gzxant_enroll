package com.gzxant.controller.enroll.enter;

import java.util.List;

import com.gzxant.controller.enroll.personnel.EnrollPersonnelController;
import com.gzxant.entity.enroll.personnel.EnrollPersonnel;
import com.gzxant.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gzxant.annotation.SLog;
import com.gzxant.base.entity.ReturnDTO;
import com.gzxant.base.vo.DataTable;
import com.gzxant.service.enroll.enter.IEnrollEnterService;
import com.gzxant.entity.enroll.enter.EnrollEnter;
import com.gzxant.util.ReturnDTOUtil;
import com.gzxant.base.controller.BaseController;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 参赛者信息 前端控制器
 * </p>
 *
 * @author tecty
 * @since 2018-07-06
 */
@Controller
@RequestMapping("/enter")
public class EnrollEnterController extends BaseController {
	@Autowired
	private IEnrollEnterService enrollEnterService;
	@Autowired
	private ISysDictService dictService;
	@Autowired
	private EnrollPersonnelController enrollPersonnelController;


	@ApiOperation(value = "进入参赛者信息列表界面", notes = "进入参赛者信息列表界面")
	@GetMapping(value = "")
	public String list(Model model) {
		model.addAttribute("enterType",dictService.getDictTree("ENTER_TYPE"));
		return "/enroll/enter/list";
	}



	@ApiOperation(value = "获取参赛者信息列表数据", notes = "获取参赛者信息列表数据:使用约定的DataTable")
	@PostMapping(value = "/list")
	@ResponseBody
	public DataTable<EnrollEnter> list(@RequestBody DataTable<EnrollEnter> dt) {
		return enrollEnterService.pageSearch(dt);
	}
	@ApiOperation(value = "进入参赛者信息编辑界面", notes = "进入参赛者信息编辑界面")
	@GetMapping(value = "/{action}/{id}")
	public String detail(@PathVariable("action") String action,@PathVariable("id") String id, Model model) {
		EnrollEnter enrollEnter = enrollEnterService.selectById(id);
		model.addAttribute("enrollEnter", enrollEnter);
		model.addAttribute("action", action);
		return "/enroll/enter/detail";
	}


	@ApiOperation(value = "添加参赛者信息", notes = "添加参赛者信息")
	@PostMapping(value = "/insert")
	@ResponseBody
	public ReturnDTO create(EnrollEnter param) {
		EnrollPersonnel enrollPersonnel=new EnrollPersonnel();
		enrollPersonnel.setPassword("123456");
		enrollPersonnel.setIdCard("440823198908216212");
		enrollPersonnel.setName(param.getName());
		enrollPersonnel.setPlace(param.getPlace());
		enrollPersonnel.setPhone("18665053437");
		enrollPersonnel.setRemark("这是后台添加用户");
		enrollPersonnel.setStyle("爵士");
		enrollPersonnelController.create(enrollPersonnel);
		Long id=enrollPersonnel.getId();
		param.setPersonnelId(enrollPersonnel.getId());
		enrollEnterService.insert(param);
		return ReturnDTOUtil.success();
	}
	@ApiOperation(value = "进入编辑参赛者信息", notes = "进入编辑参赛者信息")
	@GetMapping(value = "/insert")
	public String importDate(Model model) {
		model.addAttribute("action", "insert");
		model.addAttribute("enterType",dictService.getDictTree("ENTER_TYPE"));
		return "/enroll/enter/insert";
	}
	@ApiOperation(value = "编辑参赛者信息", notes = "编辑参赛者信息")
	@PostMapping(value = "/update")
	@ResponseBody
	public ReturnDTO update(EnrollEnter param) {
		enrollEnterService.updateById(param);
		return ReturnDTOUtil.success();
	}

	@SLog("批量删除参赛者信息")
	@ApiOperation(value = "批量删除参赛者信息", notes = "批量删除参赛者信息")
	@PostMapping(value = "/delete")
	@ResponseBody
	public ReturnDTO delete(@RequestParam("ids") List<Long> ids) {
		boolean success = enrollEnterService.deleteBatchIds(ids);
		if (success) {
			return ReturnDTOUtil.success();
		}
		return ReturnDTOUtil.fail();
	}
}
