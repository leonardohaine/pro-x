package br.com.prox.controller;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import lombok.Data;

@Named
@Data
public class GraficoBean {

	private LineChartModel areaModel;

	@PostConstruct
	public void init() {
		createAreaModel();
	}

	public LineChartModel getAreaModel() {
		return areaModel;
	}

	private void createAreaModel() {
		areaModel = new LineChartModel();

		LineChartSeries boys = new LineChartSeries();
		boys.setFill(true);
		boys.setLabel("Boys");
		boys.set("2004", 120);
		boys.set("2005", 100);
		boys.set("2006", 44);
		boys.set("2007", 150);
		boys.set("2008", 25);

		LineChartSeries girls = new LineChartSeries();
		girls.setFill(true);
		girls.setLabel("Girls");
		girls.set("2004", 52);
		girls.set("2005", 60);
		girls.set("2006", 110);
		girls.set("2007", 90);
		girls.set("2008", 120);

		areaModel.addSeries(boys);
		areaModel.addSeries(girls);

		areaModel.setTitle("Horas por projeto");
		areaModel.setLegendPosition("ne");
		areaModel.setStacked(true);
		areaModel.setShowPointLabels(true);

		Axis xAxis = new CategoryAxis("Período");
		areaModel.getAxes().put(AxisType.X, xAxis);
		Axis yAxis = areaModel.getAxis(AxisType.Y);
		yAxis.setLabel("Horas");
		yAxis.setMin(0);
		yAxis.setMax(300);
	}

}
