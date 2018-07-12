package poslovna.informatika.poslovna.validation;

import poslovna.informatika.poslovna.model.RobnaKartica;

public class RobnaKarticaValidator extends Validator {
    private RobnaKartica robnaKartica;

    public RobnaKarticaValidator(RobnaKartica robnaKartica) {
        this.robnaKartica = robnaKartica;
    }

    @Override
    public boolean test() {
        boolean isValid = true;

        if(robnaKartica.getPoslovnaGodina() == null) {
            isValid = false;
            addResult("Niste uneli poslovnu godinu.");
        }

        if(robnaKartica.getPocetnoStanjeKol() <= 0) {
            isValid = false;
            addResult("Pocetno stanje kolicine ne moze biti <= 0.");
        }

        if(robnaKartica.getPocetnoStanjeVr() <= 0) {
            isValid = false;
            addResult("Pocetno stanje vrednosti ne moze biit <=0.");
        }

        if(robnaKartica.getCena() <= 0) {
            isValid = false;
            addResult("Cena ne moze biti <= 0.");
        }

        if(robnaKartica.getRoba() == null) {
            isValid = false;
            addResult("Niste izabrali robu.");
        }

        return isValid;
    }

}
