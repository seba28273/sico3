import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import region, {
  RegionState
} from 'app/entities/region/region.reducer';
// prettier-ignore
import country, {
  CountryState
} from 'app/entities/country/country.reducer';
// prettier-ignore
import location, {
  LocationState
} from 'app/entities/location/location.reducer';
// prettier-ignore
import department, {
  DepartmentState
} from 'app/entities/department/department.reducer';
// prettier-ignore
import banco, {
  BancoState
} from 'app/entities/banco/banco.reducer';
// prettier-ignore
import creditDetalle, {
  CreditDetalleState
} from 'app/entities/credit-detalle/credit-detalle.reducer';
// prettier-ignore
import credit, {
  CreditState
} from 'app/entities/credit/credit.reducer';
// prettier-ignore
import cliente, {
  ClienteState
} from 'app/entities/cliente/cliente.reducer';
// prettier-ignore
import recaudador, { RecaudadorState } from 'app/entities/recaudador/recaudador.reducer';
// prettier-ignore
import recaudadorDetalle, { RecaudadorDetalleState } from 'app/entities/recaudador-detalle/recaudador-detalle.reducer';
import token, { TokenState } from 'app/entities/token/token.reducer';
import transferencia, { TransferenciaState } from 'app/entities/transferencia/transferencia.reducer';
import cuotasvencidas, { CuotasVencidasState } from 'app/entities/cuotasvencidas/cuotasvencidas.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly region: RegionState;
  readonly country: CountryState;
  readonly location: LocationState;
  readonly department: DepartmentState;
  readonly banco: BancoState;
  readonly creditDetalle: CreditDetalleState;
  readonly credit: CreditState;
  readonly cliente: ClienteState;
  readonly recaudador: RecaudadorState;
  readonly recaudadorDetalle: RecaudadorDetalleState;
  readonly token: TokenState;
  readonly transferencia: TransferenciaState;
  readonly cuotasvencidas: CuotasVencidasState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  region,
  country,
  location,
  department,
  banco,
  creditDetalle,
  credit,
  cliente,
  recaudador,
  recaudadorDetalle, token, transferencia, cuotasvencidas,
    /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
