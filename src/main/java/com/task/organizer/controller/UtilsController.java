package com.task.organizer.controller;

import com.task.organizer.dto.EndpointsInfoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/utils")
public class UtilsController {

	private final RequestMappingHandlerMapping handlerMapping;

	public UtilsController(RequestMappingHandlerMapping handlerMapping) {
		this.handlerMapping = handlerMapping;
	}


	@GetMapping(value = "/help")
	public List<EndpointsInfoDto> show() {
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = this.handlerMapping.getHandlerMethods();
		List<EndpointsInfoDto> endpointsInfos = new ArrayList<>();
		for (var handlerMethod : handlerMethods.entrySet()) {
			EndpointsInfoDto infoDto = new EndpointsInfoDto();
			infoDto.setPaths(handlerMethod.getKey().getPathPatternsCondition().getPatterns());
			infoDto.setMethods(handlerMethod.getKey().getMethodsCondition().getMethods());
			endpointsInfos.add(infoDto);
		}
		return endpointsInfos;
	}
}
