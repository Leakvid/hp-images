import Route from '@ember/routing/route';

export default Route.extend({
    modelName: 'encounter',
    model(){
        return this.store.findAll('encounter');
    }
});