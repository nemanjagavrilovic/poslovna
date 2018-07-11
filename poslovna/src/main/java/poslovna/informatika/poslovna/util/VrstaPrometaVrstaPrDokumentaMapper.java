package poslovna.informatika.poslovna.util;

import poslovna.informatika.poslovna.model.VrstaPrDokumenta;
import poslovna.informatika.poslovna.model.VrstaPrometa;

public class VrstaPrometaVrstaPrDokumentaMapper {
    public static VrstaPrometa mapVrstaPrometaToVrstaPrDokumenta(VrstaPrDokumenta vrstaPrDokumenta) {
        switch(vrstaPrDokumenta) {
            case OT: return VrstaPrometa.OT;
            case PR: return VrstaPrometa.PR;
            case MM: return VrstaPrometa.MM;
            default: return null;
        }
    }

}
