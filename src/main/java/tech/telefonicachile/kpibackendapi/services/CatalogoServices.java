package tech.telefonicachile.kpibackendapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.telefonicachile.kpibackendapi.dtos.internals.ObjectValueDto;
import tech.telefonicachile.kpibackendapi.dtos.response.DatasourcesResponse;
import tech.telefonicachile.kpibackendapi.repository.ICatalogosRepository;

import java.util.List;

@Service
public class CatalogoServices {

    @Autowired
    private ICatalogosRepository catalogosRepository;

    public List<DatasourcesResponse> getListDatasource(){
        return catalogosRepository.getDatasource();
    }

    public List<ObjectValueDto> getListReportTypes(){
        return catalogosRepository.getTipoReporte();
    }
}
