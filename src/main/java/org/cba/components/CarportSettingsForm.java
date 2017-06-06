package org.cba.components;

import org.cba.domain.Carport;
import org.cba.domain.Frame;
import org.cba.domain.Material;
import org.cba.domain.MaterialLength;
import org.cba.model.carport.calculation.CarportSettings;
import org.cba.model.carport.calculation.CarportSettingsHolder;
import org.cba.model.carport.calculation.Dimensions;
import org.cba.parameter.ParameterFilter;
import org.cba.parameter.ParsedParameters;
import org.cba.parameter.exception.ParameterParserException;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by adam on 24/05/2017.
 * This utility class has methods for creating jsp form components and parsing send data from these form components
 */
public class CarportSettingsForm {
    public static void createFormComponents(HttpServletRequest request, Frame frame, CarportSettings settings) {
        Material borderMaterial = frame.getUpperPillarMaterial();
        SelectBuilder widthSelect = new SelectBuilder("Width", "frameWidth", settings.getFrameDimensions().width);
        SelectBuilder lengthSelect = new SelectBuilder("Length", "frameLength", settings.getFrameDimensions().length);
        for (MaterialLength materialLength : borderMaterial.getMaterialLengths()) {
            widthSelect.addOption(materialLength.getLength(), materialLength.getLength());
            lengthSelect.addOption(materialLength.getLength(), materialLength.getLength());
        }
        request.setAttribute("lengthSelect", lengthSelect);
        request.setAttribute("widthSelect", widthSelect);
        request.setAttribute("shedLength", settings.getShedDimensions().length);
        request.setAttribute("withShed", settings.isWithShed());
    }

    public static CarportSettings getRequestedCarportSettings(HttpServletRequest request, Carport defaultSettings) throws ParameterParserException {
        ParameterFilter parameterFilter = new ParameterFilter();
        parameterFilter.addInteger("frameWidth").setDefaultValue(defaultSettings.getFrameDimensions().width);
        parameterFilter.addInteger("frameLength").setDefaultValue(defaultSettings.getFrameDimensions().length);
        parameterFilter.addInteger("shedLength").setDefaultValue(defaultSettings.getShedDimensions().length);
        parameterFilter.addBoolean("withShed").setDefaultValue(defaultSettings.isWithShed());
        ParsedParameters parameters = parameterFilter.parseParameters(request);
        return mapRequestedSettings(parameters);
    }

    @NotNull
    private static CarportSettings mapRequestedSettings(ParsedParameters parameters) {
        Dimensions frameDimensions = new Dimensions(parameters.getInteger("frameLength"), parameters.getInteger("frameWidth"));
        Dimensions shedDimensions = new Dimensions(parameters.getInteger("shedLength"), 0);
        return new CarportSettingsHolder(frameDimensions, shedDimensions, parameters.getBoolean("withShed"));
    }

    public static CarportSettings getRequestedCarportSettings(HttpServletRequest request) throws ParameterParserException {
        ParameterFilter parameterFilter = new ParameterFilter();
        parameterFilter.addInteger("frameWidth").setRequired();
        parameterFilter.addInteger("frameLength").setRequired();
        parameterFilter.addInteger("shedLength").setDefaultValue(0);
        parameterFilter.addBoolean("withShed");
        ParsedParameters parameters = parameterFilter.parseParameters(request);
        return mapRequestedSettings(parameters);
    }
}
