package com.task.organizer.dto;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.pattern.PathPattern;

import java.util.Set;

public class EndpointsInfoDto {
	private Set<PathPattern> paths;
	private Set<RequestMethod> methods;

	public Set<PathPattern> getPaths() {
		return paths;
	}

	public void setPaths(Set<PathPattern> paths) {
		this.paths = paths;
	}

	public Set<RequestMethod> getMethods() {
		return methods;
	}

	public void setMethods(Set<RequestMethod> methods) {
		this.methods = methods;
	}
}
