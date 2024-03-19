package tech.telefonicachile.kpibackendapi.helper;

public enum EnumDatasource {
    REMEDY_INC_ING_SISTEMAS(1), EXTERNO_INC_IYR_INGSYS_SEMANAL(2), EXTERNO_INC_IYR_INGSYS_MENSUAL(3), REMEDY_CRQ_INGENIERIA_TASK(4), EXTERNO_CM_CUADRO_MANDO_INGSYS(5), REMEDY_CM_CUADRO_MANDO_PROVEEDORES(6);

    private int index;
    EnumDatasource(int index)
    {
        this.index = index;
    }
    public int getIndex() { return index;}
}
