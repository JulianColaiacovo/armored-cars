var gulp = require('gulp');
var concat = require('gulp-concat');
var watch = require('gulp-watch');

gulp.task('components', function () {
    return gulp.src([
        'components/angular/angular.min.js',
        'components/angular-bootstrap/ui-bootstrap-tpls.min.js',
        'components/angular-cookies/angular-cookies.min.js',
        'components/angular-messages/angular-messages.min.js',
        'components/angular-resource/angular-resource.min.js',
        'components/angular-route/angular-route.min.js',
        'components/angular-translate/angular-translate.min.js',
        'components/angular-translate-loader-static-files/angular-translate-loader-static-files.min.js',
        'components/flow.js/dist/flow.min.js',
        'components/ng-flow/dist/ng-flow.min.js',
        'components/lodash/lodash.min.js',
        'components/ng-csv/build/ng-csv.min.js',
        'components/angular-sanitize/angular-sanitize.min.js',
        'components/ng-file-upload/ng-file-upload.min.js',
        'components/ng-file-upload/ng-file-upload-shim.min.js',
        'components/html2canvas/dist/html2canvas.min.js',
        'components/jquery/dist/jquery.min.js',
        'components/moment/min/moment.min.js',
        'components/angular-md5/angular-md5.min.js',
        'components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js'
    ])
        .pipe(concat('components.js'))
        .pipe(gulp.dest('dist/'));
});

gulp.task('scripts', function () {
    return gulp.src(['app/config/**/*.js', 'app/controllers/**/*.js', 'app/routes/**/*.js', 'app/services/**/*.js', 'app/formatters/**/*.js'])
        .pipe(concat('script.js'))
        .pipe(gulp.dest('dist/'));
});

gulp.task('watch', ['scripts'], function () {
    watch('app/**/*.js', function () {
        gulp.src(['app/config/**/*.js', 'app/controllers/**/*.js', 'app/routes/**/*.js', 'app/services/**/*.js'])
            .pipe(concat('script.js'))
            .pipe(gulp.dest('dist/')).on('error', errorHandler);
    });
});

gulp.task('default', ['scripts', 'components']);

// Handle the error
function errorHandler(error) {
    console.log(error.toString());
    this.emit('end');
}