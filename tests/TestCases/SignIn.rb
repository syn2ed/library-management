#{{{ Marathon
require_fixture 'default'
#}}} Marathon

def test

    java_recorded_version = '1.8.0_231'

    with_window("Username") {
        click("Inscription")
    }

    with_window("Password") {
        select("Identifiant", "test1")
        select("Password", "test1")
        select("Repeat", "test1")
        select("E-mail", "test1@live.fr")
        click("Demander l'inscription")
    }

    with_window("Message") {
        click("OK")
    }

end
