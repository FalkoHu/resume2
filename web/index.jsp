<!DOCTYPE HTML>
<!--
	Forty by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>Landing - Forty by HTML5 UP</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="assets/css/main.css" />
		<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
		<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
	</head>
	<body>

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Header -->
				<jsp:include page="header.jsp?page=home" />

				<!-- Banner -->
				<!-- Note: The "styleN" class below should match that of the header element. -->
					<section id="banner" class="style2">
						<div class="inner">
							<span class="image">
								<img src="./images/background.jpg" alt="" />
							</span>
							<header class="major">
								<h1>Create Your Personalized IT Resume</h1>
							</header>
							<div class="content">
								<p>Get a perfectly tailored Resume<br />
								designed specificially for Illinois State University students.</p>
                                                                <ul class="actions">
										<li><a href="#one" class="button next scrolly">Get Started</a></li>
									</ul>
							</div>
						</div>
					</section>

				<!-- Main -->
					<div id="main">

						<!-- One -->
							<section id="one" class="spotlights">
								<section>
									<a href="createResume.jsp?type=Modern" class="image">
										<img src="./images/modern.png" alt="" data-position="center center" />
									</a>
									<div class="content">
										<div class="inner">
											<header class="major">
												<h3>Modern</h3>
											</header>
											<p>Want a colorful & modern Resume? Our Modern template has everything you need to create a great looking and functional resume. </p>
											<ul class="actions">
												<li><a href="createResume.jsp?type=Modern" class="button">Create Modern</a></li>
											</ul>
										</div>
									</div>
								</section>
								<section>
									<a href="createResume.jsp?type=Traditional" class="image">
										<img src="./images/traditional.png" alt="" data-position="top center" />
									</a>
									<div class="content">
										<div class="inner">
											<header class="major">
												<h3>Traditional</h3>
											</header>
											<p>This template allows for more information in a simple and classic resume design. </p>
											<ul class="actions">
												<li><a href="createResume.jsp?type=Traditional" class="button">Create Traditional</a></li>
											</ul>
										</div>
									</div>
								</section>
							</section>


				<!-- Footer -->
					<footer id="footer">
						<div class="inner">
							<ul class="icons">
								<li><a href="#" class="icon alt fa-twitter"><span class="label">Twitter</span></a></li>
								<li><a href="#" class="icon alt fa-facebook"><span class="label">Facebook</span></a></li>
								<li><a href="#" class="icon alt fa-instagram"><span class="label">Instagram</span></a></li>
								<li><a href="#" class="icon alt fa-github"><span class="label">GitHub</span></a></li>
								<li><a href="#" class="icon alt fa-linkedin"><span class="label">LinkedIn</span></a></li>
							</ul>
							<ul class="copyright">
								<li>&copy; Untitled</li><li>Design: <a href="https://html5up.net">HTML5 UP</a></li>
							</ul>
						</div>
					</footer>

			</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.scrolly.min.js"></script>
			<script src="assets/js/jquery.scrollex.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/util.js"></script>
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="assets/js/main.js"></script>

	</body>
</html>