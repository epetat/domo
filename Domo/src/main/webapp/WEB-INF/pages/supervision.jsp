<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" 	 uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/pages/entete.jsp"></jsp:include>

<script type="text/javascript">
	function submitAction(pAction) {
		try {
			with(document.frmSupervision) {
				action.value = pAction;
				submit();
			}
		} catch (exception) {
			alert(exception.description + " dans la methode submitAction");
		}
	}
</script>

<form name="frmSupervision" method="post">
	<input type="hidden" name="action">

	<header class="row">
		<div class="col-lg-12">
			<h1>SUPERVISION</h1>
		</div>
	</header>
	
	<div class="row">
		<section class="col-sm-4 table-responsive">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Les sondes</h3>
				</div>
				<table class="table table-bordered table-striped table-condensed">
					<tr>
						<th>Température intérieur (°C)</th>
						<td>${releves.temperatureInterieur.temperature}</td>
					</tr>
					<tr>
						<th>Température cheminée (°C)</th>
						<td>${releves.temperatureCheminee.temperature}</td>
					</tr>
				</table>
			</div>
		</section>
		<section class="col-sm-4 table-responsive">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">La VMC</h3>
				</div>
				<table class="table table-bordered table-striped table-condensed">
					<tr>
						<th>Température aspiré (°C)</th>
						<td>${releves.vmc.temperatureAspire.temperature}</td>
					</tr>
					<tr>
						<th>Température entrant (°C)</th>
						<td>${releves.vmc.temperatureEntrant.temperature}</td>
					</tr>
					<tr>
						<th>Température insufle (°C)</th>
						<td>${releves.vmc.temperatureInsufle.temperature}</td>
					</tr>
					<tr>
						<th>Température insuflé court (°C)</th>
						<td>${releves.vmc.temperatureInsufleCourt.temperature}</td>
					</tr>
					<tr>
						<th>Température insuflé long (°C)</th>
						<td>${releves.vmc.temperatureInsufleLong.temperature}</td>
					</tr>
					<tr>
						<th>Température sortant (°C)</th>
						<td>${releves.vmc.temperatureSortant.temperature}</td>
					</tr>
					<tr>
						<th>Rendement</th>
						<td>${releves.vmc.rendementVmc}</td>
					</tr>
				</table>
			</div>
		</section>
		<section class="col-sm-4 table-responsive">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Le moteur</h3>
				</div>
				<table class="table table-bordered table-striped table-condensed">
					<tr>
						<th>Activation du moteur</th>
						<td>${releves.moteur.moteurActif}</td>
					</tr>
					<tr>
						<th>Vitesse du moteur</th>
						<td>
							<div class="row">
								<div class="col-lg-4">
									${releves.moteur.vitesse}
								</div> 
								<div class="col-lg-4">
									<input type="text" class="form-control" style="text-align:right" name="vitesseSaisie">
								</div>
								<div class="col-lg-4">
									<button class="btn btn-default" type="button" onclick='submitAction("actualiser");'>Valider</button>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6">
									<button class="btn btn-default" type="button" onclick='submitAction("diminuer");'>-10</button>
								</div>
								<div class="col-lg-6">
									<button class="btn btn-default" type="button" onclick='submitAction("augmenter");'>+10</button>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</section>
	</div>

	<br/><br/>	
	<input type="button" value="Démarrer" onclick='submitAction("demarrer");'>
	<input type="button" value="Arrêter" onclick='submitAction("arreter");'>
</form>

<jsp:include page="/WEB-INF/pages/basDePage.jsp"></jsp:include>
