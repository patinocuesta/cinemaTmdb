$(document).ready(function(){
    $('#pagination-list li:first-child').addClass('active');
    document.getElementById(clicked_id).classList.add('active');
    $('#navfavoris li:first-child').addClass('active');
});

function getMovieList(clicked_id) {
    $('#navfavoris').children('.active').removeClass('active');
    document.getElementById(clicked_id).parentElement.classList.add('active');
    var urlapi = '/api/films/' + clicked_id + '/';
    var page = 1;
    $('#pagination-list').children('.active').removeClass('active');
    $('#pagination-list li:first-child').addClass('active');
    ajaxFun(page, urlapi);
}

function pgNav(clicked_id) {
    var tipo = $('#navfavoris > li.active > a').attr('id');
    var urlapi = '/api/films/' + tipo + '/';
    var page = clicked_id;
    $('#pagination-list').children('.active').removeClass('active');
    var index = parseInt(document.getElementById(clicked_id).innerText);
    $('#pagination-list li:nth-child(' + index + ')').addClass('active');
    ajaxFun(page, urlapi);
}

function ajaxFun(page, urlapi) {
    $("#row-films").empty();
    $.ajax({
        url: urlapi + page,
        type: 'get',
        dataType: 'json',
        contentType: 'application/json',
        data: 'data',
        success: function (data) {
            for (var i = 0; i < data.listFilmDto.length; i++) {
                $("#row-films").append(
                    ' <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 portafolio" id="col-films">' +
                    '                <div class="thumbnail">' +
                    '                    <img  class="img-responsive" src="https://image.tmdb.org/t/p/w500/' + data.listFilmDto[i].poster_path + '"/>' +
                    '                    <div class="middle">' +
                    '                         <button type="button" class="btn btn-primary btn-md" onclick="openNav(this.id)" id="' + data.listFilmDto[i].id + '">' + data.listFilmDto[i].title + '</button>' +
                    '                    </div>' +
                    '                </div>' +
                    '            </div>'
                );
            }
        }
    });
}

function openNav(clicked_id) {
    $('#pagination-list').children('.active').hide();
    $('#navbarfull').hide();
    $('#film-title').empty();
    $('#overview').empty();
    $('body').css('overflow', 'hidden');
    document.getElementById("poster_path").src = '';
    $("#panel-body-cast").empty();
    $.ajax({
        url: '/api/tmdb/list/film/' + clicked_id,
        type: 'get',
        dataType: 'json',
        contentType: 'application/json',
        data: 'data',
        success: function (data) {
            var filmTitle = data.title;
            var overview = data.overview;
            var poster_path = data.poster_path;
            var countryList = "";
            var countries = data.production_countries;
            var genres = data.genres;
            var cast = data.credits.cast;
            var crew = data.credits.crew;
            var release_date = data.release_date;
            var original_title = data.original_title;
            var original_language = data.original_language;
            var runtime = data.runtimeFilm;
            var nomrealisateur = crew[0].name;
            var fotorealisateur = crew[0].profile_path;
            for (var i = 0; i < genres.length; i++) {
                $('#genres').append(
                    '<ul Style="list-style-type: none; margin: 0;padding: 0;"><li><h4><span class="label label-default">' + genres[i].name + '</span></h4></li></ul>'
                );
            }
            countryList += countries[0].name;
            for (var i = 1; i < countries.length; i++) {
                countryList += ', ' + countries[i].name;
            }
            $('#country-film').html(countryList);
            $('#film-title').html(filmTitle);
            $('#overview').html(overview);
            $('#release_date').html(release_date);
            $('#original_title').html(original_title);
            $('#original_language').html(original_language);
            $('#runtime').html(runtime);
            $('#nomrealisateur').html(nomrealisateur);
            document.getElementById("poster_path").src = 'https://image.tmdb.org/t/p/w500/' + poster_path;
            document.getElementById("fotorealisateur").src = 'https://image.tmdb.org/t/p/w500/' + fotorealisateur;
            for (var i = 0; i < 4; i++) {
                $("#panel-body-cast").append(
                    '<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 col-artist">' +
                    '<div class="panel panel-default">' +
                    '<div class="panel-heading">' +
                    cast[i].name +
                    '</div>' +
                    '<div class="panel-body">' +
                    '<img src="https://image.tmdb.org/t/p/w500/' + cast[i].profile_path + '" ' +
                    'alt="Avatar" class="image_overlay">' +
                    '</div>' +
                    '<div class="panel-footer">' +
                    'Role:</br>' + cast[i].character +
                    '</div>' +
                    '</div>' +
                    '</div>'
                );
            }
        }
    });
    document.getElementById("overSide").style.width = "100%";
}

function closeNav() {
    document.getElementById("overSide").style.width = "0%";
    $('#genres').empty();
    $('#pagination-list').children('.active').show();
    $('#navbarfull').show();
    $('body').css('overflow', 'auto');
    $('#film-title').empty();
    $('#overview').empty();
    document.getElementById("poster_path").src = '';
    $("#panel-body-cast").empty();
}
