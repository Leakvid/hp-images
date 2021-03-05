import EmberRouter from '@ember/routing/router';
import config from 'hpapp/config/environment';

export default class Router extends EmberRouter {
  location = config.locationType;
  rootURL = config.rootURL;
}

Router.map(function () {});
Router.map(function() {
  this.route('monster', function(){
    this.route('details', { path: '/:photo_id' });
  });
});
